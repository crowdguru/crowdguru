package org.crowdguru.datastore.helpers.impl;

import org.crowdguru.datastore.constants.GuruConstants;
import org.crowdguru.datastore.domain.User;
import org.crowdguru.datastore.helpers.GuruHelper;

public class GuruHelperImpl implements GuruHelper, GuruConstants {

	@Override
	public User guru1() {
		User guru = new User();
		guru.setForename(GURU_1_FORENAME);
		guru.setSurname(GURU_1_SURNAME);
		return guru;
	}

}
