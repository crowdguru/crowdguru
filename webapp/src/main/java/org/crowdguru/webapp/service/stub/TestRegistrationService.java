package org.crowdguru.webapp.service.stub;

import org.crowdguru.service.domain.RegistrationService;
import org.crowdguru.service.request.RegistrationRequest;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("test")
public class TestRegistrationService implements RegistrationService {

	public TestRegistrationService() {
		log().warn("Stub service active");
	}
	@Override
	public void register(RegistrationRequest request) {
		
	}
}
