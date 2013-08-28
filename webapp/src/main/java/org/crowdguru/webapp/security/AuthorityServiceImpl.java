package org.crowdguru.webapp.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.crowdguru.datastore.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

public class AuthorityServiceImpl implements AuthorityService {

	@Override
	public Collection<GrantedAuthority> createAuthorities(User.Type type) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		switch (type) {
		case GURU:
			authorities.add(createGuruAuthority());
			break;
		case KEYCONTACT:
			authorities.add(createKeyContactAuthority());
			break;
		case BOTH:
			authorities.add(createGuruAuthority());
			authorities.add(createKeyContactAuthority());
		default:
			break;
		}
		
		return authorities;
	}
	
	private GrantedAuthority createGuruAuthority() {
		return createAuthority(Type.ROLE_GURU.toString());
	}
	
	private GrantedAuthority createKeyContactAuthority() {
		return createAuthority(Type.ROLE_KEYCONTACT.toString());
	}
	
	private GrantedAuthority createAuthority(String name) {
		return new SimpleGrantedAuthority(name);
	}
}
