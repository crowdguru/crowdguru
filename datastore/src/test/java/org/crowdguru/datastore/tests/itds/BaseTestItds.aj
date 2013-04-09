package org.crowdguru.datastore.tests.itds;

import org.crowdguru.datastore.repositories.UserRepository;
import org.crowdguru.datastore.tests.BaseTest;

public privileged aspect BaseTestItds {

	public UserRepository BaseTest.getGuruRepository() {
		return guruRepository;
	}

}
