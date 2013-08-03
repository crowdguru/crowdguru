package org.crowdguru.datastore.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class HasId {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	protected Long id;
}
