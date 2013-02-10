package org.crowdguru.datastore.context;

import org.crowdguru.datastore.repositories.GuruRepository;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Singletons {

	public static synchronized GuruRepository guruRepository() {
		if (_guruRepository == null) {
			_guruRepository = context().getBean(GuruRepository.class);
		}
		return _guruRepository;
	}
	
	public static synchronized ConfigurableApplicationContext context() {
		if (_context == null) {
			_context = new AnnotationConfigApplicationContext(CrowdGuruContext.class);
		}
		return _context;
	}

	private static GuruRepository _guruRepository;
	private static ConfigurableApplicationContext _context;
}
