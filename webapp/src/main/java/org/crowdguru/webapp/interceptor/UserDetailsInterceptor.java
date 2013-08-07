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
		Object principle = getPrinciple();
		if (principle instanceof UserDetails) {
			modelAndView.addObject(USER_DETAILS_KEY, (UserDetails)principle);
		}
	}
	
	private Object getPrinciple() {
		return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
