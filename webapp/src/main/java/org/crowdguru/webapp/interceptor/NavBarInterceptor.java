package org.crowdguru.webapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.crowdguru.service.domain.NotificationService;
import org.crowdguru.service.gateway.NotificationRepositoryGateway;
import org.crowdguru.webapp.security.SecurityAccessor;
import org.crowdguru.webapp.service.NotificationServiceGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mysql.jdbc.log.Log;

public class NavBarInterceptor extends HandlerInterceptorAdapter {

	private SecurityAccessor securityAccessor;
	
	private NotificationServiceGateway notificationService;
	
	@Autowired
	public void setSecurityAccessor(SecurityAccessor securityAccessor){
		this.securityAccessor = securityAccessor;
	}
	
	@Autowired
	public void setSecurityAccessor(NotificationServiceGateway notificationService){
		this.notificationService = notificationService;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler, ModelAndView modelAndView)throws Exception {
		if(isNavBarPresent(modelAndView)){
			if(securityAccessor.isCurrentUserGuru()){
				prepareModelForGuru(modelAndView);
			}else if(securityAccessor.isCurrentUserKeyContact()){
				prepareModelForKeyContact(modelAndView);
			}
		}
	}

	private boolean isNavBarPresent(ModelAndView modelAndView){
		return modelAndView != null && !modelAndView.getViewName().equals("jsonView");
	}

	private void prepareModelForGuru(ModelAndView modelAndView){
		populateNotifications(modelAndView);
	}
	
	private void prepareModelForKeyContact(ModelAndView modelAndView) {
		populateNotifications(modelAndView);
	}
	
	private void populateNotifications(ModelAndView modelAndView){
		String email = securityAccessor.getCurrentUserEmail();
		modelAndView.addObject("notifications", notificationService.getNotificationsByUserEmail(email));
	}

}