package org.crowdguru.datastore.domain.itds;

import org.crowdguru.datastore.domain.Notification;
import org.crowdguru.datastore.domain.Task;
import org.crowdguru.datastore.domain.User;

public privileged aspect NotificationItds {
	
	public User Notification.getUser() {
		return user;
	}

	public void Notification.setUser(User user) {
		this.user = user;
	}

	public Task Notification.getTask() {
		return task;
	}

	public void Notification.setTask(Task task) {
		this.task = task;
	}

	public String Notification.getMessage() {
		return message;
	}

	public void Notification.setMessage(String message) {
		this.message = message;
	}

	public boolean Notification.isUnread() {
		return unread;
	}

	public void Notification.setUnread(boolean unread) {
		this.unread = unread;
	}
}
