package org.crowdguru.webapp.service;

import java.util.List;

import org.crowdguru.datastore.domain.Cause;
import org.crowdguru.service.domain.CauseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CauseServiceGateway {
	
	private CauseService causeService;
	
	public CauseServiceGateway() {
		log().info("activity=create");
	}

	@Autowired
	public void setCauseService(CauseService causeService){
		log().info("activity=setCauseService");
		this.causeService = causeService;
	}
	
	public List<Cause> findAll(){
		return this.causeService.getCauses();
	}

	public Cause findOne(Long causeId) {
		return this.causeService.getCauseById(causeId);
	}
}
