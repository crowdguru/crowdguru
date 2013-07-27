package org.crowdguru.datastore.domain.itds;

import java.util.Set;

import org.crowdguru.datastore.domain.Sector;
import org.crowdguru.datastore.domain.Cause;
import org.crowdguru.datastore.domain.KeyContact;

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
	
	public String Cause.getShortProfile() {
		return shortProfile;
	}

	public void Cause.setShortProfile(String shortProfile) {
		this.shortProfile = shortProfile;
	}
	
	public KeyContact Cause.getKeyContact(){
		return keyContact;
	}
	
	public void Cause.setKeyContact(KeyContact keyContact){
		this.keyContact = keyContact;
	}
	
	public Set<Sector> Cause.getSectors(){
		return this.sectors;
	}
	
	public void Cause.setSectors(Set<Sector> sectors){
		this.sectors = sectors;
	}
}
