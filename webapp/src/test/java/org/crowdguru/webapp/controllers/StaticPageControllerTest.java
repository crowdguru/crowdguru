package org.crowdguru.webapp.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import javax.servlet.http.HttpServletRequest;

import org.crowdguru.datastore.repositories.UserRepository;
import org.crowdguru.webapp.controller.StaticPageController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(MockitoJUnitRunner.class)
public class StaticPageControllerTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private StaticPageController cut;
	
	@Mock(name="context")
	private HttpServletRequest mockRequest;
	
	private static final String CONTEXT_PATH = "/test";
	
	@Before
	public void setUp(){
		this.mockMvc = standaloneSetup(cut).build();
		Mockito.when(mockRequest.getContextPath()).thenReturn(CONTEXT_PATH);
	}
	
	@Test
	public void mapsStaticPages() throws Exception {
		this.mockMvc.perform(get("/pages/something"))
			.andExpect(status().isOk());
	}
	
	@Test
	public void mapsAnyStaticPages() throws Exception {
		this.mockMvc.perform(get("/pages/about"))
			.andExpect(status().isOk());
		this.mockMvc.perform(get("/pages/contact"))
			.andExpect(status().isOk());
	}
	
	@Test
	public void mapsOnlyAfterPages() throws Exception {
		this.mockMvc.perform(get("/contact"))
			.andExpect(status().isNotFound());
		
		this.mockMvc.perform(get("/index"))
			.andExpect(status().isNotFound());
	}
}
