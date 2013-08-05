package org.crowdguru.webapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ContextPathInterceptor extends HandlerInterceptorAdapter {

	private static final String CONTEXT_PATH_KEY = "contextPath";

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if(modelAndView != null) {	
			if(modelAndView.getModel().containsKey(CONTEXT_PATH_KEY) == false) {
			modelAndView.addObject(CONTEXT_PATH_KEY, getContextPath(request));
			}
		}
	}
	
	private String getContextPath(HttpServletRequest request) {
		return request.getContextPath() + "/";
	}
}
