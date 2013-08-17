package org.crowdguru.webapp.integration.view;

import org.crowdguru.webapp.helper.AuthorityFactory;
import org.junit.Before;
import org.junit.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RegisterPageTest extends PageTest {
	
	@Before
	public void setAuthority(){
		setAuthority(AuthorityFactory.createAnoymousAuthority());
	}
	
	@Test
	public void  LogOnButtonExists() throws Exception{
		this.mockMvc.perform(get("/register"))
			.andExpect(status().isOk())
			.andExpect(xpath(".//*[@id='buttonLogon']").exists());
	}
	
	@Test
	public void forenameInputExists() throws Exception{
		this.mockMvc.perform(get("/register"))
			.andExpect(status().isOk())
			.andExpect(xpath("//input[@name='forename']").exists());
	}
	
	@Test
	public void surnameInputExists() throws Exception{
		this.mockMvc.perform(get("/register"))
			.andExpect(status().isOk())
			.andExpect(xpath("//input[@name='surname']").exists());
	}
	
	@Test
	public void emailInputExists() throws Exception{
		this.mockMvc.perform(get("/register"))
			.andExpect(status().isOk())
			.andExpect(xpath("//input[@name='email']").exists());
	}
	
	@Test
	public void passwordInputExists() throws Exception{
		this.mockMvc.perform(get("/register"))
			.andExpect(status().isOk())
			.andExpect(xpath("//input[@name='password']").exists());
	}
	
	@Test
	public void submitButtonExists() throws Exception{
		this.mockMvc.perform(get("/register"))
			.andExpect(status().isOk())
			.andExpect(xpath("//input[@value='Submit']").exists());
	}
	
	@Test
	public void GuruCheckboxExists() throws Exception{
		this.mockMvc.perform(get("/register"))
			.andExpect(status().isOk())
			.andExpect(xpath("//input[@name='guru']").exists());
	}
	
	@Test
	public void keyContactCheckboxExists() throws Exception{
		this.mockMvc.perform(get("/register"))
			.andExpect(status().isOk())
			.andExpect(xpath("//input[@name='keyContact']").exists());
	}
}
