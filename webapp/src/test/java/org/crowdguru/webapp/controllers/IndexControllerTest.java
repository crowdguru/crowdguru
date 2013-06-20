package org.crowdguru.webapp.controllers;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

public class IndexControllerTest{
	
	private MockMvc mockMvc;
	
	@Before
	public void setup(){
		this.mockMvc = standaloneSetup(new IndexController()).build();
	}
	
	@Test
	public void testrootShouldMapped() throws Exception{
		this.mockMvc.perform(get("/"))
			.andExpect(status().isOk());
		
	}
	
	@Test
	public void rootShouldForwardedToIndex() throws Exception{
		this.mockMvc.perform(get("/"))
			.andExpect(forwardedUrl("index"));
	}
	
	@Test
	public void contextPathAttributeShouldAdded() throws Exception{
		this.mockMvc.perform(get("/"))
			.andExpect(model().attribute("contextPath", "/"));
	}
}
