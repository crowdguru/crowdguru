package org.crowdguru.datastore.repositories;

import org.crowdguru.datastore.domain.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, Long> {

}
