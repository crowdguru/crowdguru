package org.crowdguru.service.domain;

import org.crowdguru.datastore.domain.User;

public interface UserService {

	public User save(User user);

	public User getUserByEmail(String email);
}
