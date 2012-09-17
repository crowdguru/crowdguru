package org.crowdguru.datastore.validators.impl;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.crowdguru.datastore.constants.GuruConstants;
import org.crowdguru.datastore.domain.Guru;
import org.crowdguru.datastore.validators.GuruValidator;

public class GuruValidatorImpl implements GuruValidator, GuruConstants {

	@Override
	public void validateGuru1(Guru guru) {
		assertNotNull(guru);
		assertEquals(GURU_1_FORENAME, guru.getForename());
		assertEquals(GURU_1_SURNAME, guru.getSurname());
	}

}
