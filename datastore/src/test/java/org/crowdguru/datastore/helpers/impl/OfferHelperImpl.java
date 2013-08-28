package org.crowdguru.datastore.helpers.impl;

import org.crowdguru.datastore.domain.Notification;
import org.crowdguru.datastore.domain.Offer;
import org.crowdguru.datastore.helpers.NotificationHelper;
import org.crowdguru.datastore.helpers.OfferHelper;
import org.crowdguru.datastore.repositories.NotificationRepository;
import org.crowdguru.datastore.repositories.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class OfferHelperImpl implements OfferHelper {

	@Autowired
	private OfferRepository offerRepository;
	
	@Override
	public void removeRelationships() {
		for(Offer offer : offerRepository.findAll()){
			offer.setUser(null);
			offer.setTask(null);
			offerRepository.save(offer);
		}
	}

}
