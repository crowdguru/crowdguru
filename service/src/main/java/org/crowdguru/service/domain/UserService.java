package org.crowdguru.service.domain;

import org.crowdguru.datastore.domain.User;

public interface UserService {

	public UserDetails save(User user);

	public UserDetails findByEmail(String email);
}
