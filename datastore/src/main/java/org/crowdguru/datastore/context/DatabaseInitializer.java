package org.crowdguru.datastore.context;

import java.util.HashSet;
import java.util.Set;

import org.crowdguru.datastore.domain.Skill;
import org.crowdguru.datastore.domain.SkillGroup;
import org.crowdguru.datastore.domain.User;
import org.crowdguru.datastore.domain.User.Type;
import org.crowdguru.datastore.repositories.SkillGroupRepository;
import org.crowdguru.datastore.repositories.SkillRepository;
import org.crowdguru.datastore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Profile("dev")
public class DatabaseInitializer implements ApplicationListener<ContextRefreshedEvent> {

	SkillRepository skillRepository;
	
	SkillGroupRepository skillGroupRepository;
	
	UserRepository userRepository;
	
	@Autowired
	public void setSkillRepository(SkillRepository skillRepository){
		this.skillRepository = skillRepository;
	}
	
	@Autowired
	public void setSkillGroupRepository(SkillGroupRepository skillGroupRepository){
		this.skillGroupRepository = skillGroupRepository;
	}
	
	@Autowired
	public void setUserRepository(UserRepository userRepository){
		this.userRepository = userRepository;
	}
	
	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
/*		if(event.getApplicationContext().getDisplayName().contains("crowdguru-application-servlet")){
			createSkillGroups();
			createUsers();
		}*/
	}
	
	private void createSkillGroups(){
		String[] skillNames = {"Skill A", "Skill B"};
		SkillGroup group = createSkillGroup("Group 1", skillNames);
		String[] skillNames2 = {"Skill C", "Skill D"};
		group = createSkillGroup("Group 2", skillNames2);
	}
	
	private SkillGroup createSkillGroup(String groupName, String[] skillNames){
		SkillGroup group = createSkillGroup(groupName);
		Set<Skill> skills = new HashSet<Skill>();
		
		for (String skillName: skillNames){
			skills.add(createSkill(skillName));
		}
		
		group.setSkills(skills);
		
		return skillGroupRepository.save(group);
	}
	
	private SkillGroup createSkillGroup(String name){
		SkillGroup group = new SkillGroup();
		group.setGroupName(name);
		return skillGroupRepository.save(group);
	}
	
	private Skill createSkill(String name){
		Skill skill = new Skill();
		skill.setName(name);
		return skillRepository.save(skill);
	}
	
	private void createUsers(){
		User user = new User();
		user.setForename("Forname1");
		user.setSurname("Surname1");
		user.setEmail("kc@crowdguru.org");
		user.setPassword("kc");
		user.setType(Type.KEYCONTACT);
		userRepository.save(user);
		
		user = new User();
		user.setForename("Forname2");
		user.setSurname("Surname2");
		user.setEmail("gr@crowdguru.org");
		user.setPassword("gr");
		user.setType(Type.GURU);
		userRepository.save(user);
	}
}
