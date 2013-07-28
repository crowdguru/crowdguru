package org.crowdguru.datastore.domain.itds;

import java.util.Set;

import org.crowdguru.datastore.domain.Sector;
import org.crowdguru.datastore.domain.Cause;
import org.crowdguru.datastore.domain.User;

public privileged aspect CauseItds {

	public Long Cause.getId() {
		return id;
	}

	public void Cause.setId(Long id) {
		this.id = id;
	}
	
	public String Cause.getName() {
		return name;
	}

	public void Cause.setName(String name) {
		this.name = name;
	}
	
	public String Cause.getDescription() {
		return description;
	}

	public void Cause.setDescription(String description) {
		this.description = description;
	}
	
	public Set<User> Cause.getKeyContacts(){
		return keyContacts;
	}
	
	public void Cause.setKeyContacts(Set<User> keyContacts){
		this.keyContacts = keyContacts;
	}
	
	public Set<Sector> Cause.getSectors(){
		return this.sectors;
	}
	
	public void Cause.setSectors(Set<Sector> sectors){
		this.sectors = sectors;
	}
}
