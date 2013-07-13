package org.crowdguru.datastore.context;

import org.crowdguru.datastore.repositories.UserRepository;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Singletons {

	public static synchronized UserRepository userRepository() {
		if (userRepository == null) {
			userRepository = context().getBean(UserRepository.class);
		}
		return userRepository;
	}

	public static synchronized ConfigurableApplicationContext context() {
		if (context == null) {
			
			context = new GenericXmlApplicationContext();
			
			//TODO: Revisit here. Expose a function to alter active profile
			// Can be changed by requesting context but too much effort
			// just to change active profile
			context.getEnvironment().addActiveProfile("prod");
			context.load("classpath:datastoreContext.xml");
			context.refresh();
		}
		
		return context;
	}

	private static UserRepository userRepository;
	private static GenericXmlApplicationContext context;
}
