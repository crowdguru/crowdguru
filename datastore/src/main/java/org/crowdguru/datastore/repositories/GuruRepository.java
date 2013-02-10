package org.crowdguru.datastore.repositories;

import org.crowdguru.datastore.domain.Guru;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuruRepository extends JpaRepository<Guru, Long> {

}
