package org.crowdguru.service.domain;

import java.util.List;
import org.crowdguru.datastore.domain.Offer;
import org.crowdguru.service.request.OfferRequest;

public interface OfferService {
	
	public Offer findOne(Long id);
	
	public List<Offer> findAll();

	public Offer create(OfferRequest request);
}
