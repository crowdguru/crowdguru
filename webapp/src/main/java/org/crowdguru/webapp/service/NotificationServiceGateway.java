package org.crowdguru.webapp.service;

import java.util.List;

import org.crowdguru.datastore.domain.Notification;
import org.crowdguru.datastore.domain.User;
import org.crowdguru.service.domain.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceGateway {

	private NotificationService notificationService;
	
	@Autowired
	public void setNotificationService(NotificationService notificationService){
		this.notificationService = notificationService;
	}
	
	public Notification getNotification(Long id){
		return notificationService.getNotification(id);
	}
	
	public List<Notification> getNotificationsByUserEmail(String userEmail){
		return notificationService.getNotificationsByUserEmail(userEmail);
	}
}
