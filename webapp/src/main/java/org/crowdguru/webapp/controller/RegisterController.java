package org.crowdguru.webapp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.crowdguru.service.exception.InvalidAccountTypeException;
import org.crowdguru.service.request.RegistrationRequest;
import org.crowdguru.webapp.service.RegistrationServiceGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/signup")
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
	public ModelAndView getForm(Model model, HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		log().info("activity=getForm");
		return new ModelAndView("signup", model.asMap());
	}
	
	@RequestMapping(method = RequestMethod.POST, produces="application/json")
	public ModelAndView signUp(@ModelAttribute RegistrationRequest request,
			BindingResult bindingResult, Model model) throws InvalidAccountTypeException{
		log().debug(request);
		registrationService.register(request);
		model.addAttribute("stat", "ok");
		return new ModelAndView("jsonView", model.asMap());
	}
}
