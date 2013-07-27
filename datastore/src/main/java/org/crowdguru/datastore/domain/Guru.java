package org.crowdguru.datastore.domain;

import java.util.Set;

public class Guru {
	
	private Long id;
	
	private String forename;

	private String surname;
	
	private String email;
	
	private String password;
	
	private Boolean emailGoodMatches;
	
	private String jobTitle;
	
	private String organization;
	
	private String location;
	
	private Boolean locationDisabled;
	
	private String shortProfile;
	
	Set<Skill> skills;
}
