package org.crowdguru.service.domain.stub;

import java.util.List;

import org.crowdguru.datastore.domain.Sector;
import org.crowdguru.service.domain.SectorService;

public class TestSectorService implements SectorService{

	private TestRepository<Sector> repository;
	
	public TestSectorService() {
		log().warn("state=created");
		repository = new TestRepository<Sector>();
		
		Sector sector1 = new Sector();
		sector1.setId(new Long(1));
		sector1.setName("Sector 1");
		
		Sector sector2 = new Sector();
		sector2.setId(new Long(2));
		sector2.setName("Sector 2");
		
		repository.save(sector1);
		repository.save(sector2);
	}

	@Override
	public Sector findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public List<Sector> findAll() {
		return repository.findAll();
	}

	@Override
	public Sector save(Sector sector) {
		return repository.save(sector);
	}
	
	
}
