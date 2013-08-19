package org.crowdguru.service.gateway;

import java.util.List;

import org.crowdguru.datastore.domain.Cause;
import org.crowdguru.datastore.domain.Sector;
import org.crowdguru.datastore.repositories.CauseRepository;
import org.crowdguru.datastore.repositories.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CauseRepositoryGateway {
	
	private CauseRepository causeRepository;
	
	public CauseRepositoryGateway(){
		log().info("activity=created");
	}
	
	@Autowired
	public void setCauseRepository(CauseRepository causeRepository) {
		log().info("activity=externalResourceBinded");
		this.causeRepository = causeRepository;
	}
	
	public List<Cause> findAll(){
		return causeRepository.findAll();
	}

	public Cause save(Cause cause) {
		return causeRepository.save(cause);
	}
	
	public Cause findOne(Long id){
		return causeRepository.findOne(id);
	}
}
