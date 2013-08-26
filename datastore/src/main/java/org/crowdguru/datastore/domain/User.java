package org.crowdguru.datastore.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
	
	@ManyToMany
	Set<Skill> skills;
	
	@ManyToMany
	private Set<Cause> causes;
	
	@OneToMany(mappedBy="owner")
	private Set<Task> ownedTasks;
	
	@Lob
	byte[] avatar;
}