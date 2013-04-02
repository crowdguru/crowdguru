package org.crowdguru.datastore.repositories;

import org.crowdguru.datastore.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
