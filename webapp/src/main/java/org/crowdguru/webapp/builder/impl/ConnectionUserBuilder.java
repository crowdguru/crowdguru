package org.crowdguru.webapp.builder.impl;

import org.crowdguru.datastore.domain.User;
import org.crowdguru.webapp.builder.Builder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UserProfile;
import org.springframework.stereotype.Component;

@Component
public class ConnectionUserBuilder implements Builder<Connection<?>, User> {

	@Override
	public User build(Connection<?> connection) {
		User user = new User();
		user.setProviderId(connection.getKey().getProviderId());
		user.setProviderUserId(connection.getKey().getProviderUserId());
		UserProfile userProfile = connection.fetchUserProfile();
		user.setForename(userProfile.getFirstName());
		user.setSurname(userProfile.getLastName());
		return user;
	}

}
