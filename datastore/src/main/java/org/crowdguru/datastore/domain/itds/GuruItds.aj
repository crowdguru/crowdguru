package org.crowdguru.datastore.domain.itds;

import java.util.Set;

import org.crowdguru.datastore.domain.Guru;
import org.crowdguru.datastore.domain.Skill;

public privileged aspect GuruItds {

	public Long Guru.getId() {
		return id;
	}

	public void Guru.setId(Long id) {
		this.id = id;
	}
	
	public String Guru.getForename(){
		return forename;
	}
	
	public void Guru.setForename(String forename){
		this.forename = forename;
	}
	
	public String Guru.getSurname(){
		return surname;
	}
	
	public void Guru.setSurname(String surname){
		this.surname = surname;
	}
	
	public String Guru.getEmail(){
		return email;
	}
	
	public void Guru.setEmail(String email){
		this.email = email;
	}
	
	public String Guru.getPassword(){
		return password;
	}
	
	public void Guru.setPassword(String password){
		this.password = password;
	}
	
	public Boolean Guru.getEmailGoodMatches(){
		return emailGoodMatches;
	}
	
	public void Guru.setEmailGoodMatches(Boolean emailGoodMatches){
		this.emailGoodMatches = emailGoodMatches;
	}
	
	public String Guru.getJobTitle(){
		return jobTitle;
	}
	
	public void Guru.setJobTitle(String jobTitle){
		this.jobTitle = jobTitle;
	}
	
	public String Guru.getOrganization(){
		return organization;
	}
	
	public void Guru.setOrganization(String organization){
		this.organization = organization;
	}
	
	public String Guru.getLocation(){
		return location;
	}
	
	public void Guru.setLocation(String location){
		this.location = location;
	}

	public Boolean Guru.getLocationDisabled(){
		return locationDisabled;
	}
	
	public void Guru.setLocationDisabled(Boolean locationDisabled){
		this.locationDisabled = locationDisabled;
	}
	
	public String Guru.getShortProfile(){
		return shortProfile;
	}
	
	public void Guru.setShortProfile(String shortProfile){
		this.shortProfile = shortProfile;
	}
	
	public Set<Skill> Guru.getSkills(){
		return skills;
	}
	
	public void Guru.setSkills(Set<Skill> skills){
		this.skills = skills;
	}
	
}
