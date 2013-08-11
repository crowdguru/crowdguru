package org.crowdguru.webapp.controller;

import org.crowdguru.webapp.controller.TaskController;
import org.crowdguru.service.request.RegistrationRequestOld;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class TaskControllerTest{
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private TaskController cut;
	
	@Before
	public void setup(){
		this.mockMvc = standaloneSetup(cut).build();
	}
	
	@Test
	public void respondsToTasksRequest() throws Exception{
		this.mockMvc.perform(get("/tasks"))
			.andExpect(status().isOk())
			.andExpect(view().name("tasks/list"));
	}
	
	@Test
	public void respondsToNewTaskRequest() throws Exception{
		this.mockMvc.perform(get("/tasks/new"))
			.andExpect(status().isOk())
			.andExpect(view().name("tasks/new"));
	}
	
	@Test
	public void respondsToSpecificTaskRequest() throws Exception{
		this.mockMvc.perform(get("/tasks/123"))
			.andExpect(status().isOk())
			.andExpect(view().name("tasks/show"));
		
		this.mockMvc.perform(get("/tasks/456"))
			.andExpect(status().isOk())
			.andExpect(view().name("tasks/show"));
	}
	
	@Test
	public void respondsToSaveRequest() throws Exception{
		this.mockMvc.perform(get("/tasks/save"))
			.andExpect(status().isOk());
	}
	
	@Test
	public void respondsToPublishRequest() throws Exception{
		this.mockMvc.perform(get("/tasks/publish"))
			.andExpect(status().isOk());
	}
}
