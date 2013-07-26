package org.crowdguru.webapp.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.crowdguru.datastore.domain.User;
import org.crowdguru.datastore.repositories.UserRepository;
import org.crowdguru.webapp.controller.UserController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private UserController cut;
	
	@Mock(name="userRepository")
	private UserRepository mockUserRepository;
	
	@Mock(name="context")
	private HttpServletRequest mockContext;
	
	private static final String CONTEXT_PATH = "/test";
	
	@Before
	public void setUp(){
		this.mockMvc = standaloneSetup(cut).build();
		Mockito.when(mockContext.getContextPath()).thenReturn(CONTEXT_PATH);
	}
	
	@Test
	public void findsAllGurus() throws Exception {
		when(mockUserRepository.findAll()).thenReturn(new ArrayList<User>());
		this.mockMvc.perform(get("/users"))
			.andExpect(status().isOk())
			.andExpect(view().name("users/list"));
	}
	
	@Test
	public void findsGuruById() throws Exception {
		when(mockUserRepository.findOne(new Long(1))).thenReturn(new User());
		this.mockMvc.perform(get("/users/1"))
			.andExpect(status().isOk())
			.andExpect(view().name("users/profile"));
	}
}
