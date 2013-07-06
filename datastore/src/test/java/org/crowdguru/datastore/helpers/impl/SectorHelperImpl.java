package org.crowdguru.datastore.helpers.impl;

import org.crowdguru.datastore.constants.SectorConstants;
import org.crowdguru.datastore.domain.Sector;
import org.crowdguru.datastore.helpers.SectorHelper;

public class SectorHelperImpl implements SectorHelper, SectorConstants {

	@Override
	public Sector sector1() {
		Sector sector = new Sector();
		sector.setName(SECTOR_1_NAME);
		return sector;
	}

	@Override
	public Sector sector2() {
		Sector sector = new Sector();
		sector.setName(SECTOR_2_NAME);
		return sector;
	}
	
	@Override
	public Sector sector3() {
		Sector sector = new Sector();
		sector.setName(SECTOR_3_NAME);
		return sector;
	}
}
