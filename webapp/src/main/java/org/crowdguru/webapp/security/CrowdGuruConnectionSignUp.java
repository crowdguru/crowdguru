package org.crowdguru.webapp.security;

import org.crowdguru.datastore.domain.User;
import org.crowdguru.datastore.repositories.UserRepository;
import org.crowdguru.webapp.builder.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

@Component
public final class CrowdGuruConnectionSignUp implements ConnectionSignUp {

	public String execute(Connection<?> connection) {
		
		String providerId = connection.getKey().getProviderId();
		String providerUserId = connection.getKey().getProviderUserId();
		
		User user = userRepository.findByProviderIdAndProviderUserId(providerId, providerUserId);
		
		if (user == null) {
			log().info("No existing user found with provider: '" 
						+ providerId + "', provider user id '" + providerUserId + "': creating new account");
			user = builder.build(connection);
			userRepository.saveAndFlush(user);
			log().info("Created new user '" + user + "'");
			
		}
		else {
			log().info("Found existing user '" + user + "'");
		}
		
		return user.getId().toString();
	}


	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private Builder<Connection<?>, User> builder;
	
}
