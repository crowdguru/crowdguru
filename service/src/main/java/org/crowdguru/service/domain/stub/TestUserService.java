package org.crowdguru.service.domain.stub;

import java.util.Collection;
import java.util.List;
import org.crowdguru.datastore.domain.User;
import org.crowdguru.service.domain.AuthorityService;
import org.crowdguru.service.domain.UserDetails;
import org.crowdguru.service.domain.UserService;
import org.crowdguru.service.domain.impl.AuthorityServiceImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class TestUserService implements UserService{

	private TestRepository<User> repository;
	
	private AuthorityService authorityService;
	
	public TestUserService() {
		log().warn("state=created");
		repository = new TestRepository<User>();
		authorityService = new AuthorityServiceImpl();
	}

	@Override
	public synchronized User save(User user) {
		return repository.save(user);
	}

	@Override
	public synchronized UserDetails findByEmail(String email) {
		User user = null;
		List<User> users = repository.findAll();
		
		for(User usr: users) {
			if(usr.getEmail().equals(email)) {
				user = usr;
				break;
			}
		}
		
		if(user == null) {
			throw new UsernameNotFoundException("User not found with " + email);
		}
		
		return createUserDetails(user);
	}
	
	private UserDetails createUserDetails(User user) {
		Collection<GrantedAuthority> authorities = authorityService.createAuthorities(user.getType());		
		return new UserDetails(user, authorities);
	}

	@Override
	public UserDetails populateUserDetails(User user) {
		return createUserDetails(user);
	}
}
