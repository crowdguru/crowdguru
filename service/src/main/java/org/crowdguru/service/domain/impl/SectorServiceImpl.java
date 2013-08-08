package org.crowdguru.service.domain.impl;

import java.util.List;

import org.crowdguru.datastore.domain.Sector;
import org.crowdguru.service.domain.SectorService;
import org.crowdguru.service.gateway.SectorRepositoryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;

public class SectorServiceImpl implements SectorService {

	private SectorRepositoryGateway sectorRepository;
	
	@Autowired
	public void setSectorRepository(SectorRepositoryGateway sectorRepositoryGateway) {
		this.sectorRepository = sectorRepositoryGateway;
	}
	
	@Override
	public Sector findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Sector> findAll() {
		return sectorRepository.findAll();
	}

	@Override
	public Sector save(Sector sector) {
		return sectorRepository.save(sector);
	}

}
