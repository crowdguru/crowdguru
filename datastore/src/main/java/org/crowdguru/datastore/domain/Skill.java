package org.crowdguru.datastore.domain;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "skill")
public class Skill extends HasId{

	@Basic
	private String name;
	
	@ManyToOne
	private SkillGroup group;
	
	@ManyToMany(mappedBy = "skills")
	private Set<User> users;
}
