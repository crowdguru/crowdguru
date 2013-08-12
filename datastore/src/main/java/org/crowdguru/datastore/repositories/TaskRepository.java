package org.crowdguru.datastore.repositories;

import org.crowdguru.datastore.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
