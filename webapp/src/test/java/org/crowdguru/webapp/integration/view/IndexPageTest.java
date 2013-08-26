package org.crowdguru.webapp.integration.view;

import org.crowdguru.webapp.helper.AuthorityFactory;
import org.junit.Before;
import org.junit.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class IndexPageTest extends PageTest{
	
	@Before
	public void setAuthority(){
		setAuthority(AuthorityFactory.createAnoymousAuthority());
	}
	
	@Test
	public void logOnButtonExists() throws Exception{
		//TODO:Integrate thymeleaf testing libs
		this.mockMvc.perform(get("/"))
			.andExpect(status().isOk());
	}
}
