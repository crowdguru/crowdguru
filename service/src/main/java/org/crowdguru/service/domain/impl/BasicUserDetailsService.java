package org.crowdguru.service.domain.impl;

import org.crowdguru.service.domain.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("basicUserDetailsService")
public class BasicUserDetailsService implements UserDetailsService{

	private UserService userService;
	
	public BasicUserDetailsService() {
		log().info("state=created");
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		log().info("activity=loadUser;email=" + email);
		return userService.findByEmail(email);
	}
}
