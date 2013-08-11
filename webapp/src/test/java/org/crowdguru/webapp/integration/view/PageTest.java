package org.crowdguru.webapp.integration.view;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.security.Principal;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml", "/service-context.xml"})
public abstract class PageTest {

	@Autowired
	protected WebApplicationContext wac;
	
	protected MockMvc mockMvc;
	
	protected SecurityContext securityContext;

	public PageTest() {
		super();
	}

	protected void setAuthority(Authentication authenticaton) {
		when(securityContext.getAuthentication()).thenReturn(authenticaton);
	}

	@Before
	public void setup() {
		this.mockMvc = webAppContextSetup(wac).build();
		this.securityContext = mock(SecurityContext.class);
		SecurityContextHolder.setContext(securityContext);
	}
}