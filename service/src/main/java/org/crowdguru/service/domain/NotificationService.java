package org.crowdguru.service.domain;

import java.util.List;

import org.crowdguru.datastore.domain.Notification;
import org.crowdguru.datastore.domain.Offer;
import org.crowdguru.datastore.domain.User;

public interface NotificationService {
	
	public Notification getNotification(Long id);
	
	public List<Notification> getNotificationsByUserEmail(String userEmail);

	public Notification createOfferNotification(Offer offer);
}
