package org.crowdguru.datastore.validators.impl;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.crowdguru.datastore.constants.SectorConstants;
import org.crowdguru.datastore.domain.Sector;
import org.crowdguru.datastore.validators.SectorValidator;

public class SectorValidatorImpl implements SectorValidator, SectorConstants {

	@Override
	public void validateSector1(Sector sector) {
		assertThat(sector, is(notNullValue()));
		assertThat("name", sector.getName(), is(equalTo(SECTOR_1_NAME)));
	}
	
	@Override
	public void validateSector2(Sector sector) {
		assertThat(sector, is(notNullValue()));
		assertThat("name", sector.getName(), is(equalTo(SECTOR_2_NAME)));
	}

}
