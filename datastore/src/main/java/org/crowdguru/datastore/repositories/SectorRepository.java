package org.crowdguru.datastore.repositories;

import org.crowdguru.datastore.domain.Sector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectorRepository extends JpaRepository<Sector, Long> {

}
