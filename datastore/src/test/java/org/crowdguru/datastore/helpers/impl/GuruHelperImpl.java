package org.crowdguru.datastore.helpers.impl;

import org.crowdguru.datastore.constants.GuruConstants;
import org.crowdguru.datastore.domain.Guru;
import org.crowdguru.datastore.helpers.GuruHelper;

public class GuruHelperImpl implements GuruHelper, GuruConstants {

	@Override
	public Guru guru1() {
		Guru guru = new Guru();
		guru.setForename(GURU_1_FORENAME);
		guru.setSurname(GURU_1_SURNAME);
		return guru;
	}

}
