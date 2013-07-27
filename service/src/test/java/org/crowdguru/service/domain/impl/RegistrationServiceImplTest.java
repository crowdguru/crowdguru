package org.crowdguru.service.domain.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.crowdguru.datastore.domain.Cause;
import org.crowdguru.datastore.domain.Guru;
import org.crowdguru.datastore.domain.KeyContact;
import org.crowdguru.datastore.domain.Sector;
import org.crowdguru.datastore.domain.Skill;
import org.crowdguru.service.domain.CauseService;
import org.crowdguru.service.domain.GuruService;
import org.crowdguru.service.domain.KeyContactService;
import org.crowdguru.service.domain.SectorService;
import org.crowdguru.service.domain.SkillService;
import org.crowdguru.service.request.RegistrationRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.containsInAnyOrder;


@RunWith(MockitoJUnitRunner.class)
public class RegistrationServiceImplTest {
	
	@InjectMocks
	RegistrationServiceImpl cut;
	
	@Mock
	GuruService guruService;
	
	@Mock
	KeyContactService keyContactService;
	
	@Mock
	CauseService causeService;
	
	@Mock
	SkillService skillService;
	
	@Mock
	SectorService sectorService;
	
	@Test
	public void createsGuruIfTypeGuru() {
		RegistrationRequest request = new RegistrationRequest();
		request.setGuru(true);
		cut.register(request);
		verify(guruService).save(any(Guru.class));
	}
	
	@Test
	public void createsAndassociatesKeyContactAndCauseIfNew() {
		RegistrationRequest request = new RegistrationRequest();
		request.setKeyContact(true);
		request.setCauseName("CAUSE_NAME");
		request.setCauseShortProfile("CAUSE_SHORT_BIO");
		
		KeyContact contact = new KeyContact();
		contact.setId(new Long(1));
		when(keyContactService.save(any(KeyContact.class))).thenReturn(contact);
		
		Cause cause = new Cause();
		cause.setId(new Long(1));
		when(causeService.save(any(Cause.class))).thenReturn(cause);
		
		cut.register(request);
		
		verify(keyContactService, times(2)).save(any(KeyContact.class));
		verify(causeService, times(2)).save(any(Cause.class));
		assertThat(contact.getCause(), equalTo(cause));
		assertThat(cause.getKeyContact(), equalTo(contact));
	}
	
	@Test
	public void createsOnlyKeyContactAndAssociatesCauseIfCauseNotNew() {
		RegistrationRequest request = new RegistrationRequest();
		request.setKeyContact(true);
		request.setCauseId(new Long(1));
		
		KeyContact contact = new KeyContact();
		contact.setId(new Long(1));
		when(keyContactService.save(any(KeyContact.class))).thenReturn(contact);
		
		Cause cause = new Cause();
		cause.setId(new Long(1));
		when(causeService.findOne(new Long(1))).thenReturn(cause);
		
		cut.register(request);
		
		verify(keyContactService, times(2)).save(any(KeyContact.class));
		verify(causeService).save(any(Cause.class));
		assertThat(contact.getCause(), equalTo(cause));
	}
	
	@Test
	public void mapsRequestToGuru() {
		ArgumentCaptor<Guru> argument = ArgumentCaptor.forClass(Guru.class);
		
		RegistrationRequest request = new RegistrationRequest();
		request.setGuru(true);
		request.setForename("FORENAME");
		request.setSurname("SURNAME");
		request.setEmail("EMAIL@EMAIL.COM");
		request.setPassword("PASSWORD");
		request.setEmailGoodMatches(true);
		request.setJobTitle("JOBTITLE");
		request.setOrganization("ORGANIZATION");
		request.setLocation("LOCATION");
		request.setLocationDisabled(true);
		request.setShortProfile("SHORTPROFILE");
		request.setAgreedTC(true);
		List<Long> skills = new ArrayList<Long>();
		skills.add(new Long(1));
		skills.add(new Long(2));
		skills.add(new Long(3));
		request.setSkills(skills);
		
		Skill skill1 = new Skill();
		skill1.setId(new Long(1));
		
		Skill skill2 = new Skill();
		skill2.setId(new Long(2));
		
		Skill skill3 = new Skill();
		skill3.setId(new Long(3));
		
		when(skillService.findOne(new Long(1))).thenReturn(skill1);
		when(skillService.findOne(new Long(2))).thenReturn(skill2);
		when(skillService.findOne(new Long(3))).thenReturn(skill3);
		
		cut.register(request);
		verify(guruService).save(argument.capture());
		Guru guru = argument.getValue();
		assertThat(guru.getForename(), equalTo("FORENAME"));
		assertThat(guru.getSurname(), equalTo("SURNAME"));
		assertThat(guru.getEmail(), equalTo("EMAIL@EMAIL.COM"));
		assertThat(guru.getPassword(), equalTo("PASSWORD"));
		assertThat(guru.getEmailGoodMatches(), equalTo(true));
		assertThat(guru.getJobTitle(), equalTo("JOBTITLE"));
		assertThat(guru.getOrganization(), equalTo("ORGANIZATION"));
		assertThat(guru.getLocation(), equalTo("LOCATION"));
		assertThat(guru.getLocationDisabled(), equalTo(true));
		assertThat(guru.getShortProfile(), equalTo("SHORTPROFILE"));
	
		Set<Skill> guruSkills = guru.getSkills();
		assertThat(guruSkills, containsInAnyOrder(skill1, skill2, skill3));
	}
	
	@Test
	public void mapsRequestToKeyContact() {
		ArgumentCaptor<KeyContact> argument = ArgumentCaptor.forClass(KeyContact.class);
		
		RegistrationRequest request = new RegistrationRequest();
		request.setKeyContact(true);
		request.setForename("FORENAME");
		request.setSurname("SURNAME");
		request.setEmail("EMAIL@EMAIL.COM");
		request.setPassword("PASSWORD");
		request.setEmailGoodMatches(true);
		request.setJobTitle("JOBTITLE");
		request.setOrganization("ORGANIZATION");
		request.setLocation("LOCATION");
		request.setLocationDisabled(true);
		request.setShortProfile("SHORTPROFILE");
		request.setAgreedTC(true);
		request.setCauseId(new Long(1));
		
		Cause cause = new Cause();
		cause.setId(new Long(1));
		when(causeService.findOne(new Long(1))).thenReturn(cause);
		
		KeyContact contact = new KeyContact();
		contact.setId(new Long(1));
		when(keyContactService.save(any(KeyContact.class))).thenReturn(contact);
		
		cut.register(request);
		
		verify(keyContactService, times(2)).save(argument.capture());
		KeyContact keyContact = argument.getAllValues().get(0);
		assertThat(keyContact.getForename(), equalTo("FORENAME"));
		assertThat(keyContact.getSurname(), equalTo("SURNAME"));
		assertThat(keyContact.getEmail(), equalTo("EMAIL@EMAIL.COM"));
		assertThat(keyContact.getPassword(), equalTo("PASSWORD"));
		assertThat(keyContact.getEmailGoodMatches(), equalTo(true));
		assertThat(keyContact.getJobTitle(), equalTo("JOBTITLE"));
		assertThat(keyContact.getOrganization(), equalTo("ORGANIZATION"));
		assertThat(keyContact.getLocation(), equalTo("LOCATION"));
		assertThat(keyContact.getLocationDisabled(), equalTo(true));
		assertThat(keyContact.getShortProfile(), equalTo("SHORTPROFILE"));
		keyContact = argument.getAllValues().get(1);
		assertThat(keyContact.getCause(), equalTo(cause));
	}
	
	@Test
	public void mapsRequestToCause() {
		ArgumentCaptor<Cause> argument = ArgumentCaptor.forClass(Cause.class);
		
		RegistrationRequest request = new RegistrationRequest();
		request.setKeyContact(true);
		request.setCauseName("NAME");
		request.setCauseShortProfile("CAUSE_SHORT_PROFILE");
		
		Cause cause = new Cause();
		cause.setId(new Long(1));
		when(causeService.save(any(Cause.class))).thenReturn(cause);
		
		KeyContact contact = new KeyContact();
		contact.setId(new Long(1));
		when(keyContactService.save(any(KeyContact.class))).thenReturn(contact);
		
		Sector sector1 = new Sector();
		sector1.setId(new Long(1));
		
		Sector sector2 = new Sector();
		sector2.setId(new Long(2));
		
		Sector sector3 = new Sector();
		sector3.setId(new Long(3));
		
		when(sectorService.findOne(new Long(1))).thenReturn(sector1);
		when(sectorService.findOne(new Long(2))).thenReturn(sector2);
		when(sectorService.findOne(new Long(3))).thenReturn(sector3);
		
		List<Long> sectors = new ArrayList<Long>();
		sectors.add(new Long(1));
		sectors.add(new Long(2));
		sectors.add(new Long(3));
		request.setSectors(sectors);
		
		cut.register(request);
		
		verify(causeService, times(2)).save(argument.capture());
		cause = argument.getAllValues().get(0);
		assertThat(cause.getName(), equalTo("NAME"));
		assertThat(cause.getShortProfile(), equalTo("CAUSE_SHORT_PROFILE"));
		
		Set<Sector> causeSectors = cause.getSectors();
		assertThat(causeSectors, containsInAnyOrder(sector1, sector2, sector3));
	}
}
