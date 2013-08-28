package org.crowdguru.service.domain.impl;

import org.crowdguru.datastore.domain.User;
import org.crowdguru.service.domain.UserService;
import org.crowdguru.service.gateway.UserRepositoryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class UserServiceImpl implements UserService{

	private UserRepositoryGateway userRepository;
	
	@Autowired
	public void setGuruRepository(UserRepositoryGateway userRepositoryGateway) {
		userRepository = userRepositoryGateway;
	}
	
	public UserServiceImpl() {
		log().info("activity=created");
	}
	
	@Override
	@Transactional
	public User save(User user){
		return userRepository.save(user);
	}

	@Override
	@Transactional(readOnly=true)
	public User getUserByEmail(String email) {
		User user = userRepository.findByEmail(email);
		log().info("activity=getUserDetailsByEmail;email=" + email + 
				";id=" + (user != null ?  user.getId() : "notfound"));
		return user;
	}
}
