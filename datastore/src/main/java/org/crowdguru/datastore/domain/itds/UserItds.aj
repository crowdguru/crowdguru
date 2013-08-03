package org.crowdguru.datastore.domain.itds;

import org.crowdguru.datastore.domain.User;
import org.crowdguru.datastore.domain.User.Type;

public privileged aspect UserItds {

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

	public String User.getShortProfile() {
		return shortProfile;
	}

	public void User.setShortProfile(String shortProfile) {
		this.shortProfile = shortProfile;
	}

	public String User.getLocation() {
		return location;
	}

	public void User.setLocation(String location) {
		this.location = location;
	}

	public String User.getEmail(){
		return email;
	}
	
	public void User.setEmail(String email){
		this.email = email;
	}
	
	public byte[] User.getAvatar(){
		return this.avatar;
	}
	
	public void User.setAvatar(byte[] avatar){
		this.avatar = avatar;
	}
	
	public void User.setPassword(String password){
		this.password = password;
	}
	
	public String User.getPassword(){
		return this.password;
	}
	
	public void User.setType(Type type) {
		this.type = type;
	}
	
	public Type User.getType(){
		return this.type;
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
