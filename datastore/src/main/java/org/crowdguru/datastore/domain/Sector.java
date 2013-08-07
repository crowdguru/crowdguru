package org.crowdguru.datastore.domain;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "sector")
public class Sector extends HasId{
	
	@Basic
	private String name;

	@ManyToMany(mappedBy = "sectors")
	private Set<Cause> causes;
}
