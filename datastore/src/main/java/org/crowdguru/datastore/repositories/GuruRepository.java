package org.crowdguru.datastore.repositories;

import org.crowdguru.datastore.domain.Guru;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface GuruRepository extends GraphRepository<Guru> {

}
