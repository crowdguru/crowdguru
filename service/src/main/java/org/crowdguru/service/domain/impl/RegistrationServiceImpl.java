package org.crowdguru.service.domain.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.crowdguru.datastore.domain.Cause;
import org.crowdguru.datastore.domain.Guru;
import org.crowdguru.datastore.domain.KeyContact;
import org.crowdguru.datastore.domain.Sector;
import org.crowdguru.datastore.domain.Skill;
import org.crowdguru.datastore.domain.SkillGroup;
import org.crowdguru.service.domain.CauseService;
import org.crowdguru.service.domain.GuruService;
import org.crowdguru.service.domain.KeyContactService;
import org.crowdguru.service.domain.RegistrationService;
import org.crowdguru.service.domain.SectorService;
import org.crowdguru.service.domain.SkillService;
import org.crowdguru.service.request.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {
	
	private GuruService guruService;
	
	private KeyContactService keyContactService;
	
	private CauseService causeService;
	
	private SkillService skillService;
	
	private SectorService sectorService;
	
	private GuruRegistrar guruRegistrar;
	
	private CauseRegistrar causeRegistrar;
	
	private KeyContactRegistrar keyContactRegistrar;
	
	@Autowired
	public RegistrationServiceImpl(GuruService guruService, KeyContactService keyContactService,
			CauseService causeService, SkillService skillService, SectorService sectorService) {
		log().warn("active");
		
		guruRegistrar = new GuruRegistrar(guruService, skillService);
		causeRegistrar = new CauseRegistrar(causeService, sectorService);
		keyContactRegistrar = new KeyContactRegistrar(keyContactService);
		
		this.keyContactService = keyContactService;
		this.causeService = causeService;
		this.guruService = guruService;
		this.skillService = skillService;
		this.sectorService = sectorService;
	}
	
	@Override
	public void register(RegistrationRequest request) {
		if(guruRegistrar.canHandle(request)) {
			log().info("request=guru");
			registerGuru(request);
		}else {
			log().info("request=keyContact");
			registerKeyContact(request);
		}
	}

	private void registerGuru(RegistrationRequest request) {
		guruRegistrar.handle(request);
	}
	
	private void registerKeyContact(RegistrationRequest request) {
		KeyContact keyContact = keyContactRegistrar.handle(request);
		Cause cause = null;
		
		if(causeRegistrar.canHandle(request)) {
			log().info("cause=new");
			cause = causeRegistrar.handle(request);
		}else {
			log().info("cause=" + request.getCauseId());
			cause = causeService.findOne(request.getCauseId());
		}

		associateKeyContactToCause(keyContact, cause);
	}

	private void associateKeyContactToCause(KeyContact keyContact, Cause cause) {
		keyContact.setCause(cause);
		cause.setKeyContact(keyContact);
		keyContactService.save(keyContact);
		causeService.save(cause);
	}

	@Override
	public List<SkillGroup> getSkillGroups() {
		log().debug("> getSkillGroups");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Sector> getSectors() {
		log().debug("> getSectors");
		// TODO Auto-generated method stub
		return null;
	}
	
	private interface Registrar<T>{

		public T handle(RegistrationRequest request);

		public boolean canHandle(RegistrationRequest request);
		
	}
	
	private class GuruRegistrar implements Registrar<Guru>{
		
		private GuruService guruService;
		private SkillService skillService;

		public GuruRegistrar(GuruService guruService, SkillService skillService) {
			this.guruService = guruService;
			this.skillService = skillService;
		}
		
		@Override
		public Guru handle(RegistrationRequest request) {
			Guru guru = constructGuru(request);
			return guruService.save(guru);
		}
		
		private Guru constructGuru(RegistrationRequest request) {
			Guru guru = new Guru();
			guru.setForename(request.getForename());
			guru.setSurname(request.getSurname());
			guru.setEmail(request.getEmail());
			guru.setPassword(request.getPassword());
			guru.setEmailGoodMatches(request.getEmailGoodMatches());
			guru.setJobTitle(request.getJobTitle());
			guru.setOrganization(request.getOrganization());
			guru.setLocation(request.getLocation());
			guru.setLocationDisabled(request.getLocationDisabled());
			guru.setShortProfile(request.getShortProfile());
			Set<Skill> skills = constructSkills(request);
			guru.setSkills(skills);
			return guru;
		}

		private Set<Skill> constructSkills(RegistrationRequest request){
			Set<Skill> skills = new HashSet<Skill>();
			
			for(Long id: request.getSkills()) {
				skills.add(skillService.findOne(id));
			}
			
			return skills;
		}
		
		@Override
		public boolean canHandle(RegistrationRequest request) {
			return request.getGuru();
		}		
	}
	
	private class CauseRegistrar implements Registrar<Cause>{
		private CauseService causeService;
		
		private SectorService sectorService;

		public CauseRegistrar(CauseService causeService, SectorService sectorService) {
			this.causeService = causeService;
			this.sectorService = sectorService;
		}
		
		@Override
		public Cause handle(RegistrationRequest request) {
			
			Cause cause = constructCause(request);
			return causeService.save(cause);
		}

		private Cause constructCause(RegistrationRequest request) {
			Cause cause = new Cause();
			cause.setName(request.getCauseName());
			cause.setShortProfile(request.getCauseShortProfile());
			Set<Sector> sectors = constructSectors(request);
			cause.setSectors(sectors);
			return cause;
		}
		
		private Set<Sector> constructSectors(RegistrationRequest request) {
			Set<Sector> sectors = new HashSet<Sector>();
			
			for(Long id: request.getSectors()) {
				sectors.add(sectorService.findOne(id));
			}
			
			return sectors;
		}

		@Override
		public boolean canHandle(RegistrationRequest request) {
			return request.getCauseId() == null;
		}
	}
	
	private class KeyContactRegistrar implements Registrar<KeyContact>{
		
		private KeyContactService keyContactService;
		
		public KeyContactRegistrar(KeyContactService keyContactService) {
			this.keyContactService = keyContactService;
		}
		
		@Override
		public KeyContact handle(RegistrationRequest request) {
			KeyContact keyContact = constructKeyContact(request);
			return keyContactService.save(keyContact);
		}

		private KeyContact constructKeyContact(RegistrationRequest request) {
			KeyContact keyContact = new KeyContact();
			keyContact.setForename(request.getForename());
			keyContact.setSurname(request.getSurname());
			keyContact.setEmail(request.getEmail());
			keyContact.setPassword(request.getPassword());
			keyContact.setEmailGoodMatches(request.getEmailGoodMatches());
			keyContact.setJobTitle(request.getJobTitle());
			keyContact.setOrganization(request.getOrganization());
			keyContact.setLocation(request.getLocation());
			keyContact.setLocationDisabled(request.getLocationDisabled());
			keyContact.setShortProfile(request.getShortProfile());
			return keyContact;
		}

		@Override
		public boolean canHandle(RegistrationRequest request) {
			return request.getKeyContact();
		}
	}
}
