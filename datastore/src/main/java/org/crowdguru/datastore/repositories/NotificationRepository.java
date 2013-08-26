package org.crowdguru.datastore.repositories;

import org.crowdguru.datastore.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface NotificationRepository extends JpaRepository<Notification, Long>, QueryDslPredicateExecutor<Notification>  {

}
