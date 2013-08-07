package org.crowdguru.datastore.repositories;

import org.crowdguru.datastore.domain.Cause;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CauseRepository extends JpaRepository<Cause, Long> {

}
