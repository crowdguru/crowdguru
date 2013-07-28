package org.crowdguru.service.gateway;

import java.util.List;

import org.crowdguru.datastore.domain.Sector;
import org.crowdguru.datastore.repositories.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class SectorRepositoryGateway {
	
	private SectorRepository sectorRepository;
	
	@Autowired
	public void setSectorRepository(SectorRepository sectorRepository) {
		this.sectorRepository = sectorRepository;
	}
	
	public List<Sector> findAll(){
		return sectorRepository.findAll();
	}

	public Sector save(Sector sector) {
		return sectorRepository.save(sector);
	}
}
