package org.crowdguru.webapp.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.crowdguru.service.exception.InvalidAccountTypeException;
import org.crowdguru.service.request.RegistrationRequest;
import org.crowdguru.webapp.service.RegistrationServiceGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.AccessDeniedHandler;
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
	public String getNewForm(Model model, HttpServletRequest request) throws IOException {
		log().info("state=prepared");
		return "signup";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String add(@ModelAttribute RegistrationRequest formData, 
			BindingResult bindingResult, Model model) throws InvalidAccountTypeException{
		log().info("state=received");
		registrationService.register(formData);
		return "done";
	}
	
	@ModelAttribute
	public void populateModel(Model model, HttpServletRequest request) {
		if(model.containsAttribute("contextPath") == false) {
			addContextPathToModel(model.asMap(), request);
		}
	}
	
	private void addContextPathToModel(Map<String, Object> map, HttpServletRequest request) {
		addToModelMap(map, "contextPath", request.getContextPath() + "/");
	};
	
	private void addToModelMap(Map<String, Object> map, String attributeName, Object attributeValue) {
		map.put(attributeName, attributeValue);
	}
}
