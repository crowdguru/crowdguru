package org.crowdguru.datastore.domain;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "cause")
public class Cause extends HasId{

	@Lob
	@Column(length=512)
	@Basic(fetch=FetchType.LAZY)
	private String description;
	
	@Basic
	private String name;
	
	@ManyToMany
	private Set<Sector> sectors;
	
	@ManyToMany(fetch=FetchType.LAZY, mappedBy="causes")
	private Set<User> keyContacts;
	
	@OneToMany(mappedBy="cause")
	private Set<Task> tasks;
}
