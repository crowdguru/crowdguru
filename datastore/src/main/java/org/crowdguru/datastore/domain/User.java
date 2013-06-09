package org.crowdguru.datastore.domain;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Basic
	private String providerId;
	
	@Basic
	private String providerUserId;
	
	@Basic
	private String forename;
	
	@Basic
	private String surname;
	
	@Basic
	private String aboutMe;

	@Basic
	private String homeTown;
	
	@ManyToMany
	private Set<Skill> skills;
	
	@ManyToMany
	private Set<Sector> sectors;

}