package org.crowdguru.datastore.domain.itds;

import java.util.Set;

import org.crowdguru.datastore.domain.Sector;
import org.crowdguru.datastore.domain.Skill;
import org.crowdguru.datastore.domain.User;

public privileged aspect UserItds {

	public Long User.getId() {
		return id;
	}

	public void User.setId(Long id) {
		this.id = id;
	}

	public String User.getProviderId() {
		return providerId;
	}

	public void User.setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String User.getProviderUserId() {
		return providerUserId;
	}

	public void User.setProviderUserId(String providerUserId) {
		this.providerUserId = providerUserId;
	}

	public String User.getForename() {
		return forename;
	}

	public void User.setForename(String forename) {
		this.forename = forename;
	}

	public String User.getSurname() {
		return surname;
	}

	public void User.setSurname(String surname) {
		this.surname = surname;
	}

	public String User.getAboutMe() {
		return aboutMe;
	}

	public void User.setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public String User.getHomeTown() {
		return homeTown;
	}

	public void User.setHomeTown(String homeTown) {
		this.homeTown = homeTown;
	}

	public Set<Skill> User.getSkills() {
		return skills;
	}

	public void User.setSkills(Set<Skill> skills) {
		this.skills = skills;
	}

	public Set<Sector> User.getSectors() {
		return sectors;
	}

	public void User.setSectors(Set<Sector> sectors) {
		this.sectors = sectors;
	}

	public String User.toString() {
		return "{ "
				+ "id: '"             + id             + "', "
				+ "forename: '"       + forename       + "', " 
				+ "surname: '"        + surname        + "', "
				+ "providerId: '"     + providerId     + "', "
				+ "providerUserId: '" + providerUserId + "', "
				+ "' }";
	}

}
