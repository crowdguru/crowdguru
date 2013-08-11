package org.crowdguru.datastore.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "skillgroup")
public class SkillGroup extends HasId{
	
	private String groupName;
	
	@OneToMany(cascade=CascadeType.ALL)
	private Set<Skill> skills;
}
