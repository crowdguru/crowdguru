package org.crowdguru.datastore.context;

import org.crowdguru.datastore.repositories.UserRepository;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Singletons {

	public static synchronized UserRepository guruRepository() {
		if (_guruRepository == null) {
			_guruRepository = context().getBean(UserRepository.class);
		}
		return _guruRepository;
	}

	public static synchronized ConfigurableApplicationContext context() {
		if (_context == null) {
			_context = new ClassPathXmlApplicationContext("datastoreContext.xml");
		}
		return _context;
	}

	private static UserRepository _guruRepository;
	private static ConfigurableApplicationContext _context;
}
