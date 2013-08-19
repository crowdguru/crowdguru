package org.crowdguru.datastore.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "cause")
public class Cause extends HasId{

	private String description;
	
	private String name;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private Set<Sector> sectors;
	
	@ManyToMany(fetch=FetchType.EAGER, mappedBy="causes")
	private Set<User> keyContacts;
}
