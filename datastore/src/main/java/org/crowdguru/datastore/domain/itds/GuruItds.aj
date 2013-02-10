package org.crowdguru.datastore.domain.itds;

import org.crowdguru.datastore.domain.Guru;

public privileged aspect GuruItds {
	
	public Long Guru.getId() {
		return id;
	}

	public void Guru.setId(Long id) {
		this.id = id;
	}

	public String Guru.getForename() {
		return forename;
	}

	public void Guru.setForename(String forename) {
		this.forename = forename;
	}

	public String Guru.getSurname() {
		return surname;
	}

	public void Guru.setSurname(String surname) {
		this.surname = surname;
	}
	
	public String Guru.toString() {
		return "{ id: '" + id + "', forename: '" + forename + "', surname: '" + surname + "' }";
	}

}
