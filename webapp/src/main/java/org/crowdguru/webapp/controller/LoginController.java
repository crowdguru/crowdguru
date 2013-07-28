package org.crowdguru.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
public class LoginController {
	
	private HttpServletRequest httpServletRequest;
	
	@Autowired
	public LoginController(HttpServletRequest httpServletRequest) {
		this.httpServletRequest = httpServletRequest;
	}
	
	@RequestMapping("/login")
	public String index(Model m) {
		return "signin";
	}
	
	@ModelAttribute
	public void populateModel(Model model, HttpServletRequest request) {
		if(model.containsAttribute("contextPath") == false) {
			model.addAttribute("contextPath", request.getContextPath() + "/");
		}
	}
}
