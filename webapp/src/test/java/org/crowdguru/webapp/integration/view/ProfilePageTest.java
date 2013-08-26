package org.crowdguru.webapp.integration.view;

import org.crowdguru.webapp.helper.AuthorityFactory;
import org.junit.Before;
import org.junit.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ProfilePageTest extends PageTest {
	
	@Test
	public void  createTaskLinkExistsIfKeyContactLoggedOn() throws Exception{
		//TODO:Integrate thymeleaf testing libs
		this.setAuthority(AuthorityFactory.createKeyContactAuthority());
		this.mockMvc.perform(get("/profile"))
			.andExpect(status().isOk());
	}
	
	@Test
	public void  doesNotCreateTaskLinkExistsIfGuruLoggedOn() throws Exception{
		//TODO:Integrate thymeleaf testing libs
		this.setAuthority(AuthorityFactory.createGuruAuthority());
		this.mockMvc.perform(get("/profile"))
			.andExpect(status().isOk());
	}
}
