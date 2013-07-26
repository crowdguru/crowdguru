package org.crowdguru.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import org.crowdguru.service.request.RegistrationRequest;
import org.crowdguru.webapp.service.RegistrationServiceGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/register")
public class RegisterController {
	
	private RegistrationServiceGateway registrationService;

	private HttpServletRequest httpServletRequest;
	
	@Autowired
	public RegisterController(RegistrationServiceGateway registrationService,
			HttpServletRequest httpServletRequest) {
		this.registrationService = registrationService;
		this.httpServletRequest = httpServletRequest;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String getNewForm(Model m) {
		return "signup";
	}
	
	
	@RequestMapping(method=RequestMethod.POST)
	public String add(@ModelAttribute RegistrationRequest request, BindingResult result, Model model) {
		registrationService.register(request);
		return "redirect:login";
	}
	
	@ModelAttribute
	public void populateModel(Model model) {
		model.addAttribute("contextPath", httpServletRequest.getContextPath());
	}
}
