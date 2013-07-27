package org.crowdguru.datastore.domain;

import java.util.Set;

public class Cause {
	Long id;
	
	String shortProfile;
	
	String name;
	
	KeyContact keyContact;
	
	Set<Sector> sectors;
}
