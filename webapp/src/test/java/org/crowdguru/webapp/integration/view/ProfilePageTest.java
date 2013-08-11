package org.crowdguru.webapp.integration.view;

import org.crowdguru.webapp.helper.AuthorityFactory;
import org.junit.Before;
import org.junit.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ProfilePageTest extends PageTest {
	
	@Test
	public void  createTaskLinkExistsIfKeyContactLoggedOn() throws Exception{
		this.setAuthority(AuthorityFactory.createKeyContactAuthority());
		this.mockMvc.perform(get("/profile"))
			.andExpect(status().isOk())
			.andExpect(xpath("//a[contains(@href, '/tasks/new')]").exists());
	}
	
	@Test
	public void  doesNotCreateTaskLinkExistsIfGuruLoggedOn() throws Exception{
		this.setAuthority(AuthorityFactory.createGuruAuthority());
		this.mockMvc.perform(get("/profile"))
			.andExpect(status().isOk())
			.andExpect(xpath("//a[contains(@href, '/tasks/new')]").doesNotExist());
	}
}
