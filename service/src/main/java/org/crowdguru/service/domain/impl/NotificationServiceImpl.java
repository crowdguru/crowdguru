package org.crowdguru.service.domain.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.crowdguru.datastore.domain.Notification;
import org.crowdguru.datastore.domain.Offer;
import org.crowdguru.datastore.domain.QNotification;
import org.crowdguru.datastore.domain.Task;
import org.crowdguru.datastore.domain.User;
import org.crowdguru.service.domain.NotificationService;
import org.crowdguru.service.gateway.NotificationRepositoryGateway;
import org.crowdguru.service.gateway.UserRepositoryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.transaction.annotation.Transactional;
import com.mysema.query.types.Predicate;

public class NotificationServiceImpl implements NotificationService {

	private NotificationRepositoryGateway notificationRepository;

	private UserRepositoryGateway userRepository;
	
	public NotificationServiceImpl(){
		log().info("activity=created");
	}
	
	@Autowired
	public void setNotificationRepository(NotificationRepositoryGateway notificationRepositoryGateway){
		this.notificationRepository = notificationRepositoryGateway;
	}
	
	@Autowired
	public void setUserRepositoryGateway(UserRepositoryGateway userRepository){
		this.userRepository = userRepository;
	}
	
	@Override
	@Transactional
	public Notification createOfferNotification(Offer offer) {
		Notification notif = new Notification();
		notif.setMessage(offer.getMessage());
		notif.setTask(offer.getTask());
		notif.setUnread(true);
		notif.setUser(offer.getUser());
		Notification saved = notificationRepository.save(notif);
		log().info("activity=createOfferNotification;id=" + saved.getId() + ";for=" + offer.getId());
		return saved;
	}

	@Override
	@Transactional(readOnly=true)
	public Notification getNotification(Long id) {
		Notification notif = notificationRepository.findOne(id);
		log().info("activity=getNotification;id=" + id + ";found=" + (notif != null ? true : false));
		return notif;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Notification> getNotificationsByUserEmail(String userEmail) {
		User user = userRepository.findByEmail(userEmail);
		List<Notification> list = new ArrayList<Notification>();
		Set<Task> tasks = user.getOwnedTasks();
		
		if(tasks.isEmpty() == false){
			Pageable page = new PageRequest(0, 5, new Sort(Direction.DESC, "id"));
			Predicate predicate = QNotification.notification.task.in(tasks);
		    
			for (Notification n : notificationRepository.findAll(predicate, page)) {
		        list.add(n);
		    }
		}
	    
		log().info("activity=getNotifications;count=" + list.size());
		return list;
	}
}
