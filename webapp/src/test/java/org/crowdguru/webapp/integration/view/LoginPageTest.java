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
		this.mockMvc.perform(get("/login"))
			.andExpect(status().isOk())
			.andExpect(xpath("//input[@name='email']").exists());
	}
	
	@Test
	public void passwordInputExists() throws Exception{
		this.mockMvc.perform(get("/login"))
			.andExpect(status().isOk())
			.andExpect(xpath("//input[@name='password']").exists());
	}
	
	@Test
	public void  submitButtonExists() throws Exception{
		this.mockMvc.perform(get("/login"))
			.andExpect(status().isOk())
			.andExpect(xpath("//input[@value='Log on']").exists());
	}
	
	@Test
	public void  registerButtonExists() throws Exception{
		this.mockMvc.perform(get("/login"))
			.andExpect(status().isOk())
			.andExpect(xpath("//a[contains(text(),'Register')]").exists());
	}
}
