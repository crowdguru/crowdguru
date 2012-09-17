package org.crowdguru.datastore.domain;

import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class Guru { 
	private String forename;
	private String surname;
}
