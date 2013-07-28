package org.crowdguru.webapp.service;

import org.crowdguru.datastore.domain.User;
import org.crowdguru.service.domain.RegistrationService;
import org.crowdguru.service.exception.InvalidAccountTypeException;
import org.crowdguru.service.request.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceGateway {
	
	private RegistrationService registrationService;
	
	@Autowired
	public RegistrationServiceGateway(RegistrationService registrationService) {
		this.registrationService = registrationService;
	}

	public User register(RegistrationRequest request) throws InvalidAccountTypeException{
		return registrationService.register(request);
	}
}
