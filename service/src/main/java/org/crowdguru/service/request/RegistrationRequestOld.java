package org.crowdguru.service.request;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class RegistrationRequestOld {
	
	private String email;
	
	private String password;
	
	private String forename;
	
	private String surname;

	private String organization;
	
	private String jobTitle;
	
	private String shortProfile;
	
	private boolean guru;
	
	private boolean keyContact;
	
	private String location;
	
	private boolean locationDisabled;
	
	private boolean agreedTC;

	private boolean emailGoodMatches;
	
	private List<Long> skills;
	
	private String causeName;
	
	private String causeShortProfile;
	
	private List<Long> sectors;
	
	private Long causeId;

	private URI avatar;
	
	public RegistrationRequestOld() {
		skills = new ArrayList<Long>();
		sectors = new ArrayList<Long>();
	}
	
	public List<Long> getSkills() {
		return skills;
	}

	public void setSkills(List<Long> skills) {
		this.skills = skills;
	}

	public String getCauseShortProfile() {
		return causeShortProfile;
	}

	public void setCauseShortProfile(String causeShortProfile) {
		this.causeShortProfile = causeShortProfile;
	}

	public List<Long> getSectors() {
		return sectors;
	}

	public void setSectors(List<Long> sectors) {
		this.sectors = sectors;
	}

	public boolean getEmailGoodMatches() {
		return emailGoodMatches;
	}

	public void setEmailGoodMatches(boolean emailGoodMatches) {
		this.emailGoodMatches = emailGoodMatches;
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

	public void setAgreedTC(boolean agreedTC) {
		this.agreedTC = agreedTC;
	}

	public String getCauseName() {
		return causeName;
	}

	public void setCauseName(String causeName) {
		this.causeName = causeName;
	}

	public Long getCauseId() {
		return causeId;
	}

	public void setCauseId(Long causeId) {
		this.causeId = causeId;
	}

	public void setAvatar(URI uri) {
		this.avatar = uri;
	}
	
	public URI getAvatar() {
		return avatar;
	}
}
