package org.crowdguru.webapp.service.stub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.crowdguru.datastore.domain.Sector;
import org.crowdguru.datastore.domain.Skill;
import org.crowdguru.datastore.domain.SkillGroup;
import org.crowdguru.service.domain.RegistrationService;
import org.crowdguru.service.request.RegistrationRequest;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("test")
public class TestRegistrationService implements RegistrationService {

	public TestRegistrationService() {
		log().warn("state=init");
	}
	
	@Override
	public void register(RegistrationRequest request) {
		
	}

	@Override
	public List<SkillGroup> getSkillGroups() {
		Set<Skill> skills1 = new HashSet<Skill>();
		Skill skill1 = new Skill();
		skill1.setName("Skill 1");
		skill1.setId(new Long(1));
		
		Skill skill2 = new Skill();
		skill2.setName("Skill 2");
		skill2.setId(new Long(2));
		skills1.add(skill1);
		skills1.add(skill2);
		
		Set<Skill> skills2 = new HashSet<Skill>();
		Skill skill3 = new Skill();
		skill3.setName("Skill 3");
		skill3.setId(new Long(3));
		
		Skill skill4 = new Skill();
		skill4.setName("Skill 4");
		skill4.setId(new Long(4));
		
		skills2.add(skill3);
		skills2.add(skill4);
		
		List<SkillGroup> groups = new ArrayList<SkillGroup>();
		SkillGroup group1 = new SkillGroup();
		group1.setSkills(skills1);
		group1.setGroupName("Skill Group 1");
		group1.setId(new Long(1));
		
		SkillGroup group2 = new SkillGroup();
		group2.setGroupName("Skill Group 2");
		group2.setSkills(skills2);
		group2.setId(new Long(2));
		
		groups.add(group1);
		groups.add(group2);
		return groups;
	}

	@Override
	public List<Sector> getSectors() {
		List<Sector> sectors1 = new ArrayList<Sector>();
		Sector sector1 = new Sector();
		sector1.setName("Sector 1");
		sector1.setId(new Long(1));
		
		Sector sector2 = new Sector();
		sector2.setName("Sector 2");
		sector2.setId(new Long(2));
		
		List<Sector> sectors = new ArrayList<Sector>();
		sectors.add(sector1);
		sectors.add(sector2);
		return sectors;
	}
}
