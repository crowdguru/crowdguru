package org.crowdguru.webapp.service;

import static org.mockito.Mockito.mock;

import org.crowdguru.service.domain.CauseService;
import org.crowdguru.service.domain.RegistrationService;
import org.crowdguru.service.domain.TaskService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MockServiceConfiguration {

	@Bean
	public RegistrationService registrationService(){
		return mock(RegistrationService.class);
	}
	
	@Bean
	public TaskService taskService(){
		return mock(TaskService.class);
	}
	
	@Bean
	public CauseService causeService(){
		return mock(CauseService.class);
	}
	
}
