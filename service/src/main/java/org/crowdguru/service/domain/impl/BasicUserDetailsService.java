package org.crowdguru.service.domain.impl;

import java.util.Collection;
import java.util.Collections;

import org.crowdguru.datastore.domain.User;
import org.crowdguru.service.domain.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
		User guru = userService.findByEmail(email);
		GrantedAuthority userAuthority = (GrantedAuthority) new SimpleGrantedAuthority("ROLE_USER");
		Collection<GrantedAuthority> authorities = Collections.singletonList(userAuthority);
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(guru.getEmail(),
				guru.getPassword(), authorities);
		return userDetails;
	}

}
