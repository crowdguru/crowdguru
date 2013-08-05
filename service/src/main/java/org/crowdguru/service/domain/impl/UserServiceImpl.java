package org.crowdguru.service.domain.impl;

import java.util.Collection;

import org.apache.commons.lang.NotImplementedException;
import org.crowdguru.datastore.domain.User;
import org.crowdguru.service.domain.AuthorityService;
import org.crowdguru.service.domain.UserDetails;
import org.crowdguru.service.domain.UserService;
import org.crowdguru.service.gateway.UserRepositoryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.GrantedAuthority;

@Profile({"cloud, prod, dev"})
public class UserServiceImpl implements UserService{

	private UserRepositoryGateway userRepository;
	
	private AuthorityService authorityService;

	@Autowired
	public void setGuruRepository(UserRepositoryGateway userRepositoryGateway) {
		userRepository = userRepositoryGateway;
	}
	
	@Autowired
	public void setAuthorityService(AuthorityService authorityService) {
		this.authorityService = authorityService;
	}
	
	public UserServiceImpl() {
		log().warn("state=created");
	}
	
	@Override
	public UserDetails save(User user) {
		user = userRepository.save(user);
		Collection<GrantedAuthority> authorities = authorityService.createAuthorities(user.getType());		
		return new UserDetails(user, authorities); 
	}

	@Override
	public UserDetails findByEmail(String email) {
		throw new NotImplementedException();
	}
}
