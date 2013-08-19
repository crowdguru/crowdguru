package org.crowdguru.service.request;

import java.util.ArrayList;
import java.util.List;

public class RegistrationRequest {
	
	private String email;
	
	private String password;
	
	private String forename;
	
	private String surname;

	private boolean guru;
	
	private boolean keyContact;
	
	private String causeName;
	
	private List<String> sectors;
	
	private String shortProfile;
	
	private List<String> specialisms;
	
	private String location;
	
	public RegistrationRequest(){
		sectors = new ArrayList<String>();
		specialisms = new ArrayList<String>();
	}
	
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

	public String getCauseName() {
		return causeName;
	}

	public void setCauseName(String causeName) {
		this.causeName = causeName;
	}

	public List<String> getSectors() {
		return sectors;
	}

	public void setSectors(List<String> sectors) {
		this.sectors = sectors;
	}

	public String getShortProfile() {
		return shortProfile;
	}

	public void setShortProfile(String shortProfile) {
		this.shortProfile = shortProfile;
	}

	public List<String> getSpecialisms() {
		return specialisms;
	}

	public void setSpecialisms(List<String> specialisms) {
		this.specialisms = specialisms;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString(){
	    StringBuilder result = new StringBuilder();
	    result.append("email=" + email);
	    result.append(";password=" + password);
	    result.append(";forename=" + forename);
	    result.append(";surname=" + surname);
	    result.append(";location=" + location);
	    result.append(";guru=" + guru);
	    result.append(";shortProfile=" + shortProfile);
	    result.append(";specialisms=" + specialisms);
	    result.append(";keycontact=" + keyContact);
	    result.append(";causeName=" + causeName);
	    result.append(";sectors=" + sectors);
	    return result.toString();
	}
	
}

