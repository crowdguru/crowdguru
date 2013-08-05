package org.crowdguru.service.gateway;

import org.crowdguru.datastore.domain.User;
import org.crowdguru.datastore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRepositoryGateway{

	UserRepository userRepository;
	
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User save(User user) {
		return userRepository.save(user);
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}
