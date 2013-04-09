package org.crowdguru.datastore.domain.itds;

import java.util.Set;

import org.crowdguru.datastore.domain.Sector;
import org.crowdguru.datastore.domain.User;

public privileged aspect SectorItds {

	public Long Sector.getId() {
		return id;
	}

	public void Sector.setId(Long id) {
		this.id = id;
	}

	public String Sector.getName() {
		return name;
	}

	public void Sector.setName(String name) {
		this.name = name;
	}

	public Set<User> Sector.getUsers() {
		return users;
	}

	public void Sector.setUsers(Set<User> users) {
		this.users = users;
	}

}
