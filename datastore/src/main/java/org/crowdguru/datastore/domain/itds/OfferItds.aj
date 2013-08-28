package org.crowdguru.datastore.domain.itds;

import org.crowdguru.datastore.domain.Offer;
import org.crowdguru.datastore.domain.Task;
import org.crowdguru.datastore.domain.User;

public privileged aspect OfferItds {
	
	public User Offer.getUser() {
		return owner;
	}

	public void Offer.setUser(User owner) {
		this.owner = owner;
	}

	public Task Offer.getTask() {
		return task;
	}

	public void Offer.setTask(Task task) {
		this.task = task;
	}

	public String Offer.getMessage() {
		return message;
	}

	public void Offer.setMessage(String message) {
		this.message = message;
	}
}
