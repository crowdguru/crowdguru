package org.crowdguru.webapp.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import org.crowdguru.datastore.repositories.UserRepository;
import org.crowdguru.webapp.controller.SkeletalController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(MockitoJUnitRunner.class)
public class SkeletalControllerTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private SkeletalController cut;
	
	@Mock(name="guruRepository")
	private UserRepository mockGuruRepository;
	
	@Before
	public void setUp(){
		this.mockMvc = standaloneSetup(cut).build();
	}
	
	@Test
	public void findsAllGurus() throws Exception {
		this.mockMvc.perform(get("/gurus"))
			.andExpect(status().isOk());
		Mockito.verify(mockGuruRepository, Mockito.times(1)).findAll();
	}

}
