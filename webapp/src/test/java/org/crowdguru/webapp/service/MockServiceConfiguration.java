package org.crowdguru.webapp.service;

import static org.mockito.Mockito.mock;

import javax.persistence.EntityManagerFactory;

import org.crowdguru.datastore.context.EntitiyManagerFactoryCreator;
import org.crowdguru.service.domain.CauseService;
import org.crowdguru.service.domain.NotificationService;
import org.crowdguru.service.domain.OfferService;
import org.crowdguru.service.domain.RegistrationService;
import org.crowdguru.service.domain.TaskService;
import org.crowdguru.service.domain.UserService;
import org.crowdguru.webapp.security.SecurityAccessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

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
	
	@Bean
	public NotificationService notificationService(){
		return mock(NotificationService.class);
	}
	
	@Bean
	public SecurityAccessor securityAccessor(){
		return mock(SecurityAccessor.class);
	}
	
	@Bean
	public EntityManagerFactory crowdguru(){
		return mock(EntityManagerFactory.class);
	}
	
	@Bean
	public OfferService offerService(){
		return mock(OfferService.class);
	}
	
	@Bean
	public UserService userService(){
		return mock(UserService.class);
	}
	
	@Bean
	public HandlerInterceptorAdapter emiv(){
		return mock(HandlerInterceptorAdapter.class);
	}
}
