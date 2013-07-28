package org.crowdguru.service.domain.stub;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.crowdguru.datastore.domain.Skill;
import org.crowdguru.datastore.domain.SkillGroup;
import org.crowdguru.service.domain.SkillGroupService;

public class TestSkillGroupService implements SkillGroupService{

	TestRepository<SkillGroup> repository;
	
	public TestSkillGroupService() {
		log().warn("state=created");
		repository = new TestRepository<SkillGroup>();
		Skill skill1 = new Skill();
		skill1.setId(new Long(1));
		skill1.setName("Skill 1");
		
		Skill skill2 = new Skill();
		skill2.setId(new Long(2));
		skill2.setName("Skill 2");
		
		Skill skill3 = new Skill();
		skill3.setId(new Long(3));
		skill3.setName("Skill 3");
		
		Skill skill4 = new Skill();
		skill4.setId(new Long(4));
		skill4.setName("Skill 4");
		
		
		Set<Skill> skills1 = new HashSet<Skill>();
		
		skills1.add(skill1);
		skills1.add(skill2);
		
		SkillGroup group1 = new SkillGroup();
		group1.setId(new Long(1));
		group1.setGroupName("SkillGroup 1");
		group1.setSkills(skills1);
		repository.save(group1);
		
		Set<Skill> skills2 = new HashSet<Skill>();
		
		skills2.add(skill3);
		skills2.add(skill4);
		
		SkillGroup group2 = new SkillGroup();
		group2.setGroupName("SkillGroup 2");
		group2.setId(new Long(2));
		group2.setSkills(skills2);
		
		repository.save(group2);
	}

	@Override
	public List<SkillGroup> findAll() {
		return repository.findAll();
	}
}
