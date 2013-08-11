package org.crowdguru.webapp.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.crowdguru.datastore.domain.User;
import org.crowdguru.datastore.repositories.UserRepository;
import org.crowdguru.webapp.controller.GuruController;
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
public class GuruControllerTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private GuruController cut;
	
	@Before
	public void setUp(){
		this.mockMvc = standaloneSetup(cut).build();
	}
	
	@Test
	public void respondsGurusRequest() throws Exception {
		this.mockMvc.perform(get("/gurus"))
			.andExpect(status().isOk())
			.andExpect(view().name("gurus/list"));
	}
	
	@Test
	public void respondsSpecificGuruRequest() throws Exception {
		this.mockMvc.perform(get("/gurus/123"))
			.andExpect(status().isOk())
			.andExpect(view().name("gurus/show"));
		
		this.mockMvc.perform(get("/gurus/456"))
			.andExpect(status().isOk())
			.andExpect(view().name("gurus/show"));
	}
}
