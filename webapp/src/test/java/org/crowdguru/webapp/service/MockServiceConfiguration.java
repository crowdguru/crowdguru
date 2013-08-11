package org.crowdguru.webapp.service;

import static org.mockito.Mockito.mock;

import org.crowdguru.service.domain.RegistrationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MockServiceConfiguration {

	@Bean
	public RegistrationService registrationService(){
		return mock(RegistrationService.class);
	}
}
