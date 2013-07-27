package org.crowdguru.datastore.domain.itds;

import org.crowdguru.datastore.domain.Cause;
import org.crowdguru.datastore.domain.KeyContact;;

public privileged aspect KeyContactItds {

	public Long KeyContact.getId() {
		return id;
	}

	public void KeyContact.setId(Long id) {
		this.id = id;
	}
	
	public String KeyContact.getForename(){
		return forename;
	}
	
	public void KeyContact.setForename(String forename){
		this.forename = forename;
	}
	
	public String KeyContact.getSurname(){
		return surname;
	}
	
	public void KeyContact.setSurname(String surname){
		this.surname = surname;
	}
	
	public String KeyContact.getEmail(){
		return email;
	}
	
	public void KeyContact.setEmail(String email){
		this.email = email;
	}
	
	public String KeyContact.getPassword(){
		return password;
	}
	
	public void KeyContact.setPassword(String password){
		this.password = password;
	}
	
	public Boolean KeyContact.getEmailGoodMatches(){
		return emailGoodMatches;
	}
	
	public void KeyContact.setEmailGoodMatches(Boolean emailGoodMatches){
		this.emailGoodMatches = emailGoodMatches;
	}
	
	public String KeyContact.getJobTitle(){
		return jobTitle;
	}
	
	public void KeyContact.setJobTitle(String jobTitle){
		this.jobTitle = jobTitle;
	}
	
	public String KeyContact.getOrganization(){
		return organization;
	}
	
	public void KeyContact.setOrganization(String organization){
		this.organization = organization;
	}
	
	public String KeyContact.getLocation(){
		return location;
	}
	
	public void KeyContact.setLocation(String location){
		this.location = location;
	}

	public Boolean KeyContact.getLocationDisabled(){
		return locationDisabled;
	}
	
	public void KeyContact.setLocationDisabled(Boolean locationDisabled){
		this.locationDisabled = locationDisabled;
	}
	
	public String KeyContact.getShortProfile(){
		return shortProfile;
	}
	
	public void KeyContact.setShortProfile(String shortProfile){
		this.shortProfile = shortProfile;
	}
	
	public void KeyContact.setCause(Cause cause){
		this.cause = cause;
	}
	
	public Cause KeyContact.getCause(){
		return cause;
	}
	
}
