package org.crowdguru.webapp.controllers;

import javax.servlet.http.HttpServletRequest;

import org.crowdguru.webapp.controller.IndexController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(MockitoJUnitRunner.class)
public class IndexControllerTest{
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private IndexController cut;
	
	@Mock(name="context")
	private HttpServletRequest mockContext;
	
	private static final String CONTEXT_PATH = "/test";
	
	@Before
	public void setup(){
		this.mockMvc = standaloneSetup(cut).build();
		Mockito.when(mockContext.getContextPath()).thenReturn(CONTEXT_PATH);
	}
	
	@Test
	public void mapsRoot() throws Exception{
		this.mockMvc.perform(get("/"))
			.andExpect(status().isOk());
	}
	
	@Test
	public void mapsRootToIndex() throws Exception{
		this.mockMvc.perform(get("/"))
			.andExpect(view().name("index"));
	}
	
	@Test
	public void putsContextToRespondModel() throws Exception{
		this.mockMvc.perform(get("/"))
			.andExpect(model().attribute("contextPath", CONTEXT_PATH));
	}
}
