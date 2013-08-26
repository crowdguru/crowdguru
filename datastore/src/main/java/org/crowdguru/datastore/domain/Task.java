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
	
	@Basic
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
	
	@ManyToOne(cascade=CascadeType.ALL)
	private User owner;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Cause cause;
	
	@ManyToMany
	Set<Skill> specialisms;

}
