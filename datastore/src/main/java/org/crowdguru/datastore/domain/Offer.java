package org.crowdguru.datastore.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "offer")
public class Offer extends HasId implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	private User owner;

	@ManyToOne
	private Task task;

	@Basic
	private String message;
}
