package org.crowdguru.webapp.configuration;

import javax.sql.DataSource;

import org.crowdguru.datastore.domain.User;
import org.crowdguru.webapp.security.SecurityContext;
import org.crowdguru.webapp.security.SimpleConnectionSignUp;
import org.crowdguru.webapp.security.SimpleSignInAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;

@Configuration
public class SocialConfiguration {

	@Bean
	public ProviderSignInController providerSignInController() {
	    return new ProviderSignInController(connectionFactoryLocator(), 
	    									usersConnectionRepository(),
	    									new SimpleSignInAdapter());
	}
	

	@Bean
	@Scope(value="request", proxyMode = ScopedProxyMode.INTERFACES)	
	public Facebook facebook() {
	    return connectionRepository().getPrimaryConnection(Facebook.class).getApi();
	}

	@Bean
	public ConnectionFactoryLocator connectionFactoryLocator() {
	    ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
	    registry.addConnectionFactory(new FacebookConnectionFactory(facebookClientId, facebookClientSecret));
	    return registry;
	}

	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public ConnectionRepository connectionRepository() {
	    User user = SecurityContext.getCurrentUser();
	    return usersConnectionRepository().createConnectionRepository(user.getId().toString());
	}
	
	@Bean
	public UsersConnectionRepository usersConnectionRepository() {
	    JdbcUsersConnectionRepository repository 
	    	= new JdbcUsersConnectionRepository(dataSource,
	    										connectionFactoryLocator(), 
	    										Encryptors.noOpText());
	    repository.setConnectionSignUp(new SimpleConnectionSignUp());
	    return repository;
	}

	
	
	@Autowired
	private DataSource dataSource;
	
	@Value("${facebook.clientId}")
	private String facebookClientId;

	@Value("${facebook.clientSecret}")
	private String facebookClientSecret;

}
