package org.crowdguru.webapp.controller;

import java.util.Set;

import org.crowdguru.datastore.domain.Task;
import org.crowdguru.datastore.domain.User;
import org.crowdguru.webapp.security.SecurityAccessor;
import org.crowdguru.webapp.service.UserServiceGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/home")

public class HomeController {

	private UserServiceGateway userService;
	
	private SecurityAccessor securityAccessor; 
	
	@Autowired
	public void setUserService(UserServiceGateway userService){
		this.userService = userService;
	}
	
	@Autowired
	public void setSecurityAccessor(SecurityAccessor securityAccessor){
		this.securityAccessor = securityAccessor;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String showProfile(Model model) {
		User user = userService.getUserByEmail(securityAccessor.getCurrentUserEmail());
		Set<Task> tasks = user.getOwnedTasks();
		return "home";
	}
}
