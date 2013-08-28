package org.crowdguru.webapp.service;

import org.crowdguru.datastore.domain.Offer;
import org.crowdguru.datastore.domain.SkillGroup;
import org.crowdguru.datastore.domain.Task;
import org.crowdguru.service.domain.OfferService;
import org.crowdguru.service.domain.TaskService;
import org.crowdguru.service.request.CreateTaskRequest;
import org.crowdguru.service.request.OfferRequest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceGateway {
	
	private OfferService offerService;
	
	public OfferServiceGateway(){
		log().warn("activity=created");
	}
	
	@Autowired
	public void setTaskService(OfferService offerService) {
		this.offerService = offerService;
	}
	
	public Offer create(OfferRequest request){
		return offerService.create(request);
	}
}
