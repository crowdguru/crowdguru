package org.crowdguru.datastore.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "task")
public class Task extends HasId implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Basic
	private String title;
	
	@Lob
	@Basic(fetch=FetchType.LAZY)
	private String shortDescription;
	
	@Lob
	@Basic(fetch=FetchType.LAZY)
	private String longDescription;
	
	@Basic
	private int amount;
	
	@Basic
	private String unit;
	
	@Lob
	@Basic(fetch=FetchType.LAZY)
	private byte[] photo;
	
	@ManyToOne
	private User owner;
	
	@ManyToOne
	private Cause cause;
	
	@ManyToMany
	private Set<Skill> specialisms;
	
	@ManyToMany
	private Set<User> assignees;
}
