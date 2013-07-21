package org.crowdguru.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

@Controller
public class RegisterController {
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String signUpForm(Model m) {
		m.addAttribute("contextPath", context.getContextPath());
		return "signup";
	}
	
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String signUp(@ModelAttribute RegistrationRequest request, Model m) {
		registrationService.register(request);
		m.asMap().clear();
		return "redirect:login";
	}
	
	@Autowired
	private HttpServletRequest context;
	
	@Autowired
	private RegistrationService registrationService;
}
