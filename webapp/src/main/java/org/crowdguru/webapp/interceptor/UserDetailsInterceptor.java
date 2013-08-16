package org.crowdguru.webapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.crowdguru.service.domain.UserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class UserDetailsInterceptor extends HandlerInterceptorAdapter {

	private static final String USER_DETAILS_KEY = "userDetails";

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if(modelAndView != null){
			populateUserDetails(modelAndView);
		}
	}
	
	private void populateUserDetails(ModelAndView modelAndView) {
		Object principal = getPrinciple();
		if (principal instanceof UserDetails) {
			boolean isGuru = ((UserDetails)principal).isGuru();
			boolean isKeyContact = ((UserDetails)principal).isKeyContact();
			modelAndView.addObject(USER_DETAILS_KEY, (UserDetails)principal);
			modelAndView.addObject("isGuru", isGuru);
			modelAndView.addObject("isKeyContact", isKeyContact);
		}
	}

	private Object getPrinciple() {
		return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
