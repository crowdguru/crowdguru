package org.crowdguru.service.gateway;

import java.util.List;

import org.crowdguru.datastore.domain.Notification;
import org.crowdguru.datastore.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import com.mysema.query.types.Predicate;

public class NotificationRepositoryGateway {
	
	private NotificationRepository notificationRepository;
	
	public NotificationRepositoryGateway(){
		log().info("activity=create");
	}
	
	@Autowired
	public void setNotificationRepository(NotificationRepository notificationRepository){
		this.notificationRepository = notificationRepository;
	}

	public Notification save(Notification notif) {
		return notificationRepository.save(notif);
	}

	public Notification findOne(Long id) {
		return notificationRepository.findOne(id);
	}

	public List<Notification> findAll() {
		return notificationRepository.findAll();
	}
	
	public Iterable<Notification> findAll(Predicate predicate){
		return notificationRepository.findAll(predicate);
	}
	
	public Iterable<Notification> findAll(Predicate predicate, Pageable pageable){
		return notificationRepository.findAll(predicate, pageable);
	}
}
