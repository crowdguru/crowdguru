package org.crowdguru.webapp.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.crowdguru.datastore.domain.User;
import org.crowdguru.service.domain.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{

	private UserService userService;
	
	public AuthenticationSuccessHandler(){
		log().info("activity=create");
	}
	
	@Autowired
	public void setUserService(UserService userService){
		this.userService = userService;
	}
	
	private User setUser(HttpSession session, String email){
		User user = userService.getUserByEmail(email);
		session.setAttribute("user", user);
		return user;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		log().info("event=AuthenticationSuccess");
		String email = authentication.getName();
		User user = setUser(request.getSession(true), email);
		log().info("activity=setUserForSession;email=" + email + ";id=" + user.getId());
		super.handle(request, response, authentication);
	}
}
