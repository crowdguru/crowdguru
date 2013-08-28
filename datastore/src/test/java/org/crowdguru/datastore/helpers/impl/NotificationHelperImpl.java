package org.crowdguru.datastore.helpers.impl;

import org.crowdguru.datastore.domain.Notification;
import org.crowdguru.datastore.helpers.NotificationHelper;
import org.crowdguru.datastore.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class NotificationHelperImpl implements NotificationHelper {

	@Autowired
	private NotificationRepository notificationRepository;
	
	@Override
	public void removeRelationships() {
		for(Notification notif : notificationRepository.findAll()){
			notif.setUser(null);
			notif.setTask(null);
			notificationRepository.save(notif);
		}
	}

}
