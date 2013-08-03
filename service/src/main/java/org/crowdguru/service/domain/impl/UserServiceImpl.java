package org.crowdguru.service.domain.impl;

import org.crowdguru.datastore.domain.User;
import org.crowdguru.service.domain.UserService;
import org.crowdguru.service.gateway.UserRepositoryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;

@Profile({"cloud, prod, dev"})
public class UserServiceImpl implements UserService{

	private UserRepositoryGateway userRepository;
	
	@Autowired
	public void setGuruRepository(UserRepositoryGateway userRepositoryGateway) {
		userRepository = userRepositoryGateway;
	}
	
	public UserServiceImpl() {
		log().warn("state=created");
	}
	
	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User findByEmail(String email) {
		return null;
	}
}
