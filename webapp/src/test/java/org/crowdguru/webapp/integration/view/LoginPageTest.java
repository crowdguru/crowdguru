package org.crowdguru.webapp.integration.view;

import org.crowdguru.webapp.helper.AuthorityFactory;
import org.junit.Before;
import org.junit.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class LoginPageTest extends PageTest{
	
	@Before
	public void setAuthority(){
		setAuthority(AuthorityFactory.createAnoymousAuthority());
	}
	
	@Test
	public void emailInputExists() throws Exception{
		//TODO:Integrate thymeleaf testing libs
		this.mockMvc.perform(get("/login"))
			.andExpect(status().isOk());
	}
	
	@Test
	public void passwordInputExists() throws Exception{
		//TODO:Integrate thymeleaf testing libs
		this.mockMvc.perform(get("/login"))
			.andExpect(status().isOk());
	}
	
	@Test
	public void  submitButtonExists() throws Exception{
		//TODO:Integrate thymeleaf testing libs
		this.mockMvc.perform(get("/login"))
			.andExpect(status().isOk());
	}
	
	@Test
	public void  registerButtonExists() throws Exception{
		//TODO:Integrate thymeleaf testing libs
		this.mockMvc.perform(get("/login"))
			.andExpect(status().isOk());
	}
}
