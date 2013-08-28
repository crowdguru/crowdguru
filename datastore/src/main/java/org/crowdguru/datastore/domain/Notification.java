package org.crowdguru.datastore.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "notification")
public class Notification extends HasId implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	private User user;

	@ManyToOne
	private Task task;
	
	@Basic
	private String message;
	
	@Basic
	private boolean unread;
}
