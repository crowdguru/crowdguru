package org.crowdguru.service.request;

public class RegistrationRequest {
	
	private String email;
	
	private String password;
	
	private String forename;
	
	private String surname;

	private String organization;
	
	private String shortProfile;
	
	private String jobTitle;
	
	private boolean guru;
	
	private boolean keyContact;
	
	private String location;
	
	private boolean locationDisabled;
	
	private boolean agreedTC;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getForename() {
		return forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getShortProfile() {
		return shortProfile;
	}

	public void setShortProfile(String shortProfile) {
		this.shortProfile = shortProfile;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public boolean getGuru() {
		return guru;
	}

	public void setGuru(boolean guru) {
		this.guru = guru;
	}

	public boolean getKeyContact() {
		return keyContact;
	}

	public void setKeyContact(boolean keyContact) {
		this.keyContact = keyContact;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public boolean getLocationDisabled() {
		return locationDisabled;
	}

	public void setLocationDisabled(boolean locationDisabled) {
		this.locationDisabled = locationDisabled;
	}

	public boolean agreedTC() {
		return agreedTC;
	}

	public void setagreedTC(boolean agreedTC) {
		this.agreedTC = agreedTC;
	}
}
