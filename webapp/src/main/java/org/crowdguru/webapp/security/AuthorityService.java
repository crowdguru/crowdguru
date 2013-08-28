package org.crowdguru.webapp.security;

import java.util.Collection;

import org.crowdguru.datastore.domain.User;
import org.springframework.security.core.GrantedAuthority;

public interface AuthorityService {
	
	public enum Type{ROLE_KEYCONTACT, ROLE_GURU}
	
	public Collection<GrantedAuthority> createAuthorities(User.Type type);
}
