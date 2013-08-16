package org.crowdguru.webapp.service;

import org.crowdguru.service.domain.RegistrationService;
import org.crowdguru.service.domain.UserDetails;
import org.crowdguru.service.exception.InvalidAccountTypeException;
import org.crowdguru.service.request.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceGateway {
	
	private RegistrationService registrationService;
	
	public RegistrationServiceGateway() {
		log().warn("activity=created");
	}

	@Autowired
	public void setRegistrationService(RegistrationService registrationService){
		this.registrationService = registrationService;
	}
	
	public UserDetails register(RegistrationRequest request) throws InvalidAccountTypeException{
		return registrationService.register(request);
	}
}
