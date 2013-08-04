package org.crowdguru.webapp.interceptor;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.crowdguru.service.domain.UserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AnonymousAccessOnlyInterceptor extends HandlerInterceptorAdapter {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) 
			throws ServletException, IOException{
		boolean result = true;
		if(isLoggedOn()){
			response.sendRedirect(request.getContextPath() + "/profile");
		}
		
		return result;
	}
	
	private boolean isLoggedOn() {
		Object userDetails = getUserDetails();
		return userDetails instanceof UserDetails;
	}

	private Object getUserDetails() {
		return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}