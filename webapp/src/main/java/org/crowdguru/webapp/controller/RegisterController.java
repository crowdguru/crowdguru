package org.crowdguru.webapp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.crowdguru.service.domain.UserDetails;
import org.crowdguru.service.exception.InvalidAccountTypeException;
import org.crowdguru.service.request.RegistrationRequest;
import org.crowdguru.webapp.service.RegistrationServiceGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/register")
public class RegisterController {
	
	private RegistrationServiceGateway registrationService;

	public RegisterController() {
		log().warn("state=created");
	}
	
	@Autowired
	public void setRegistrationService(RegistrationServiceGateway registrationService) {
		this.registrationService = registrationService;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String getForm(Model model, HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		log().info("activity=getForm");
		return "/signup";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String add(@ModelAttribute RegistrationRequest formData, 
			BindingResult bindingResult, Model model) throws InvalidAccountTypeException{
		log().info("state=received");
		registrationService.register(formData);
		return "done";
	}
	
	private boolean isLoggedOn() {
		Object userDetails = getUserDetails();
		return userDetails instanceof UserDetails;
	}

	private Object getUserDetails() {
		return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
