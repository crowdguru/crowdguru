package org.crowdguru.service.domain;

import org.crowdguru.datastore.domain.User;
import org.crowdguru.service.exception.InvalidAccountTypeException;
import org.crowdguru.service.request.RegistrationRequest;

public interface RegistrationService {

	public User register(RegistrationRequest request) throws InvalidAccountTypeException;
}
