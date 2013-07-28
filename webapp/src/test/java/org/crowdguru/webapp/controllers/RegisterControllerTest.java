package org.crowdguru.webapp.controllers;

import javax.servlet.http.HttpServletRequest;
import org.crowdguru.webapp.controller.RegisterController;
import org.crowdguru.service.request.RegistrationRequestOld;
import org.crowdguru.webapp.service.RegistrationServiceGateway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.openqa.grid.web.servlet.handler.SeleniumBasedResponse;
import org.openqa.selenium.lift.HamcrestWebDriverTestCase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class RegisterControllerTest{
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private RegisterController cut;
	
	@Mock
	private HttpServletRequest httpServletRequest;
	
	@Mock
	private RegistrationServiceGateway registrationService;
	
	private static final String CONTEXT_PATH = "/test/";
	
	@Before
	public void setup(){
		this.mockMvc = standaloneSetup(cut).build();
		Mockito.when(httpServletRequest.getContextPath()).thenReturn("/test");
	}
	
	@Test
	public void responsesToRegisterGetRequests() throws Exception{
		this.mockMvc.perform(get("/register"))
			.andExpect(status().isOk());
	}
	
	@Test
	public void rendersRegistrationFormOnGetREquest() throws Exception{
		this.mockMvc.perform(get("/register"))
			.andExpect(view().name("signup"));
	}
	
	@Test
	public void redirectsOnRegisterPostRequests() throws Exception{
		RegistrationRequestOld request = new RegistrationRequestOld();
		this.mockMvc.perform(post("/register", request))
			.andExpect(status().isOk());
	}
}
