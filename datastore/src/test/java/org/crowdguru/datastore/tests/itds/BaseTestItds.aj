package org.crowdguru.datastore.tests.itds;

import org.crowdguru.datastore.repositories.GuruRepository;
import org.crowdguru.datastore.tests.BaseTest;

public privileged aspect BaseTestItds {

	public GuruRepository BaseTest.getGuruRepository() {
		return guruRepository;
	}

}
