package org.crowdguru.datastore.domain.itds;

import java.util.Set;

import org.crowdguru.datastore.domain.SkillGroup;
import org.crowdguru.datastore.domain.Skill;

public privileged aspect SkillGroupItds {

	public Long SkillGroup.getId() {
		return id;
	}

	public void SkillGroup.setId(Long id) {
		this.id = id;
	}

	public String SkillGroup.getGroupName() {
		return groupName;
	}

	public void SkillGroup.setGroupName(String groupName) {
		this.groupName = groupName;
	}
		
	public Set<Skill> SkillGroup.getSkills() {
		return skills;
	}

	public void SkillGroup.setSkills(Set<Skill> skills) {
		this.skills = skills;
	}
}
