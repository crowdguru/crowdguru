package org.crowdguru.datastore.context;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.crowdguru.datastore.domain.Cause;
import org.crowdguru.datastore.domain.Skill;
import org.crowdguru.datastore.domain.SkillGroup;
import org.crowdguru.datastore.domain.User;
import org.crowdguru.datastore.domain.User.Type;
import org.crowdguru.datastore.repositories.CauseRepository;
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
	
	CauseRepository causeRepository;
	
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
	
	@Autowired
	public void setCauseRepository(CauseRepository causeRepository){
		this.causeRepository = causeRepository;
	}
	
	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(event.getApplicationContext().getDisplayName().contains("crowdguru-application-servlet")){
			List<SkillGroup> skillGroups = createSkillGroups();
			List<User> users = createUsers();
			List<Cause> causes = createCauses();
			Set<Cause> ca = new HashSet<Cause>();
			ca.add(causes.get(0));
			users.get(0).setCauses(ca);
			userRepository.save(users.get(0));
			Set<User> us = new HashSet<User>();
			us.add(users.get(0));
			causes.get(0).setKeyContacts(us);
			causeRepository.save(causes.get(0));
		}
	}

	private List<SkillGroup> createSkillGroups(){
		List<SkillGroup> groups = new ArrayList<SkillGroup>();
		String[] skillNames = {"Skill A", "Skill B"};
		groups.add(createSkillGroup("Group 1", skillNames));
		String[] skillNames2 = {"Skill C", "Skill D"};
		groups.add(createSkillGroup("Group 2", skillNames2));
		return groups;
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
	
	private List<User> createUsers(){
		List<User> users = new ArrayList<User>();
		User user = new User();
		user.setForename("Bob");
		user.setSurname("James");
		user.setEmail("kc@crowdguru.org");
		user.setPassword("kc");
		user.setType(Type.KEYCONTACT);
		users.add(userRepository.save(user));
		
		user = new User();
		user.setForename("John");
		user.setSurname("Brains");
		user.setEmail("gr@crowdguru.org");
		user.setPassword("gr");
		user.setType(Type.GURU);
		users.add(userRepository.save(user));
		return users;
	}
	
	private List<Cause> createCauses() {
		List<Cause> causes = new ArrayList<Cause>();
		Cause cause = new Cause();
		cause.setName("Test Charity");
		cause.setDescription("At vero eos et accusamus et iusto odio dignissimos ducimus, qui blanditiis praesentium voluptatum deleniti atque corrupti, quos dolores et quas molestias excepturi sint, obcaecati cupiditate non provident, similique sunt in culpa, qui officia deserunt mollitia animi, id est laborum et dolorum fuga.");
		causes.add(causeRepository.save(cause));
		return causes;
	}
}
