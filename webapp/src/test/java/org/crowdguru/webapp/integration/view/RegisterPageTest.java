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
	public void forenameInputExists() throws Exception{
		this.mockMvc.perform(get("/register"))
			.andExpect(status().isOk())
			.andExpect(xpath("//input[@id='inputForename']").exists());
	}
	
	@Test
	public void surnameInputExists() throws Exception{
		this.mockMvc.perform(get("/register"))
			.andExpect(status().isOk())
			.andExpect(xpath("//input[@id='inputSurname']").exists());
	}
	
	@Test
	public void emailInputExists() throws Exception{
		this.mockMvc.perform(get("/register"))
			.andExpect(status().isOk())
			.andExpect(xpath("//input[@id='inputEmail']").exists());
	}
	
	@Test
	public void passwordInputExists() throws Exception{
		this.mockMvc.perform(get("/register"))
			.andExpect(status().isOk())
			.andExpect(xpath("//input[@id='inputPassword']").exists());
	}
	
	@Test
	public void submitButtonExists() throws Exception{
		this.mockMvc.perform(get("/register"))
			.andExpect(status().isOk())
			.andExpect(xpath("//input[@id='inputSubmitRegistrationForm']").exists());
	}
	
	@Test
	public void GuruCheckboxExists() throws Exception{
		this.mockMvc.perform(get("/register"))
			.andExpect(status().isOk())
			.andExpect(xpath("//input[@id='checkBoxGuru']").exists());
	}
	
	@Test
	public void keyContactCheckboxExists() throws Exception{
		this.mockMvc.perform(get("/register"))
			.andExpect(status().isOk())
			.andExpect(xpath("//input[@id='checkBoxKeyContact']").exists());
	}
}
