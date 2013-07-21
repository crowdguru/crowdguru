package org.crowdguru.webapp.controller;

import org.crowdguru.datastore.domain.User;
import org.crowdguru.datastore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

	public void register(RegistrationRequest request) {
		log().info("Request received");
		User user = new User();
		user.setForename(request.getForename());
		user.setSurname(request.getSurname());
		user = userRepository.save(user);
	}

	@Autowired
	UserRepository userRepository;
}
