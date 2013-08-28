package org.crowdguru.webapp.security;

import java.util.Collection;

import org.crowdguru.datastore.domain.User;
import org.crowdguru.service.domain.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.crowdguru.webapp.security.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService{

	private UserService userService;
	
	private AuthorityService authorityService;
		
	public UserDetailsService() {
		log().info("activity=created");
	}
		
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Autowired
	public void setAuthorityService(AuthorityService authorityService){
		this.authorityService = authorityService;
	}
		
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		log().info("activity=loadUser;email=" + email);
		User user = userService.getUserByEmail(email); 
		return createUserDetails(user);
	}
	
	private UserDetails createUserDetails(User user){
		Collection<GrantedAuthority> authorities = authorityService.createAuthorities(user.getType());		
		return new UserDetails(user, authorities); 
	}
}
