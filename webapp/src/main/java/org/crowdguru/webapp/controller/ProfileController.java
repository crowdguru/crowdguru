package org.crowdguru.webapp.controller;

import org.crowdguru.webapp.service.UserServiceGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/profile")
public class ProfileController {

	private UserServiceGateway userService;
	
	@Autowired
	public void setUserService(UserServiceGateway userService){
		this.userService = userService;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String showProfile(Model model) {
		return "profile";
	}
}
