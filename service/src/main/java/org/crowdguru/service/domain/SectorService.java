package org.crowdguru.service.domain;

import java.util.List;

import org.crowdguru.datastore.domain.Sector;

public interface SectorService {

	Sector findOne(Long id);

	List<Sector> findAll();
	
	Sector save(Sector sector);
}
