package org.crowdguru.datastore.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cause")
public class Cause extends HasId{

	private String description;
	
	private String name;
	
	@ManyToMany
	private Set<Sector> sectors;
	
	@ManyToMany
	private Set<User> keyContacts;
}
