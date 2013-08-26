package org.crowdguru.service.gateway;

import java.util.List;

import org.crowdguru.datastore.domain.Offer;
import org.crowdguru.datastore.repositories.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class OfferRepositoryGateway {
	
	private OfferRepository offerRepository;
	
	public OfferRepositoryGateway(){
		log().info("activity=create");
	}
	
	@Autowired
	public void setNotificationRepository(OfferRepository offerRepository){
		this.offerRepository = offerRepository;
	}

	public Offer save(Offer offer) {
		return offerRepository.save(offer);
	}

	public Offer findOne(Long id) {
		return offerRepository.findOne(id);
	}

	public List<Offer> findAll() {
		return offerRepository.findAll();
	}
}
