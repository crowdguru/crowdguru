package org.crowdguru.webapp.controllers;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest{
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private IndexController cut;
	
	@Mock(name="context")
	private HttpServletRequest mockContext;
	
	private static final String CONTEXT_PATH = "/test";
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		this.mockMvc = standaloneSetup(cut).build();
		Mockito.when(mockContext.getContextPath()).thenReturn(CONTEXT_PATH);
	}
	
	@Test
	public void rootShouldMapped() throws Exception{
		this.mockMvc.perform(get("/"))
			.andExpect(status().isOk());
	}
	
	@Test
	public void rootShouldReturnIndexView() throws Exception{
		this.mockMvc.perform(get("/"))
			.andExpect(view().name("index"));
	}
	
	@Test
	public void rootShouldPutContextPathToModel() throws Exception{
		this.mockMvc.perform(get("/"))
			.andExpect(model().attribute("contextPath", CONTEXT_PATH));
		Mockito.verify(mockContext, Mockito.atLeastOnce()).getContextPath();
	}
}
