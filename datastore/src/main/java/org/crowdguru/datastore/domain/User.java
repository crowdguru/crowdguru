package org.crowdguru.datastore.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User extends HasId implements Serializable{

	private static final long serialVersionUID = 1L;

	public enum Type{GURU, KEYCONTACT, BOTH}

	@Enumerated
	private Type type;
	
	@Basic
	private String email;
	
	@Basic
	private String password;
	
	@Basic
	private String providerId;
	
	@Basic
	private String providerUserId;
	
	@Basic
	private String forename;
	
	@Basic
	private String surname;
	
	@Basic
	private String shortProfile;
	
	@Lob
	@Basic(fetch=FetchType.LAZY)
	private String longProfile;

	@Basic
	private String location;
	
	@Basic
	private Boolean emailGoodMatches;
	
	@Basic
	private String jobTitle;
	
	@Basic
	private String organization;
	
	@Basic
	private Boolean isLocationDisabled;
	
	@ManyToMany(fetch=FetchType.EAGER)
	Set<Skill> skills;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private Set<Cause> causes;
	
	@Lob
	byte[] avatar;
}