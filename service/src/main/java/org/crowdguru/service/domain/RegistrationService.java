package org.crowdguru.service.domain;

import org.crowdguru.service.exception.InvalidAccountTypeException;
import org.crowdguru.service.request.RegistrationRequest;

public interface RegistrationService {

	public UserDetails register(RegistrationRequest request) throws InvalidAccountTypeException;
}
