package org.crowdguru.datastore.domain.itds;

import java.util.Set;

import org.crowdguru.datastore.domain.Skill;
import org.crowdguru.datastore.domain.User;

public privileged aspect SkillItds {

	public Long Skill.getId() {
		return id;
	}

	public void Skill.setId(Long id) {
		this.id = id;
	}

	public String Skill.getName() {
		return name;
	}

	public void Skill.setName(String name) {
		this.name = name;
	}
		
	public Set<User> Skill.getUsers() {
		return users;
	}

	public void Skill.setUsers(Set<User> users) {
		this.users = users;
	}

}
