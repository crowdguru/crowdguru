package org.crowdguru.service.domain;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.crowdguru.datastore.domain.User;

public class UserDetails extends org.springframework.security.core.userdetails.User {

	private static final long serialVersionUID = 1L;
	
	private org.crowdguru.datastore.domain.User delegate;
	
	public UserDetails(User user, Collection<GrantedAuthority> authorities) {
		super(user.getEmail(), user.getPassword(), authorities);
		delegate = user;
	}
	
	public String getEmail() {
		return delegate.getEmail();
	}
	
	public String getForename() {
		return delegate.getForename();
	}
	
	public String getSurname() {
		return delegate.getSurname();
	}
	
	public User.Type getType(){
		return delegate.getType();
	}
	
	public boolean isGuru(){
		return delegate.getType() == User.Type.GURU || delegate.getType() == User.Type.BOTH;
	}

	public boolean isKeyContact() {
		return delegate.getType() == User.Type.KEYCONTACT || delegate.getType() == User.Type.BOTH;
	}
}
