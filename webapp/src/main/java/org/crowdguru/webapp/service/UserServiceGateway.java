package org.crowdguru.webapp.service;

import org.crowdguru.datastore.domain.User;
import org.crowdguru.service.domain.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceGateway {

	private UserService userService;
	
	public UserServiceGateway(){
		log().info("activity=created");
	}
	
	@Autowired
	public void setUserService(UserService userService){
		this.userService = userService;
	}
	
	public User getUserByEmail(String email){
		return userService.getUserByEmail(email);
	} 
	
}
