package org.crowdguru.service.request;

public class RegistrationRequest {
	
	private String email;
	
	private String password;
	
	private String forename;
	
	private String surname;

	private boolean guru;
	
	private boolean keyContact;

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

	public boolean isGuru() {
		return guru;
	}

	public void setGuru(boolean guru) {
		this.guru = guru;
	}

	public boolean isKeyContact() {
		return keyContact;
	}

	public void setKeyContact(boolean keyContact) {
		this.keyContact = keyContact;
	}
}
