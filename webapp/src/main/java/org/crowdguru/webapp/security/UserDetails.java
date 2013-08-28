package org.crowdguru.webapp.security;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.crowdguru.datastore.domain.User;

public class UserDetails extends org.springframework.security.core.userdetails.User {

	private static final long serialVersionUID = 1L;
	
	public UserDetails(User user, Collection<GrantedAuthority> authorities) {
		super(user.getEmail(), user.getPassword(), authorities);
	}
}
