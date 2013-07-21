package org.crowdguru.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
public class RegisterController {
	
	@RequestMapping("/register")
	public String index(Model m) {
		log().info("Context path '" + context.getContextPath() + "'");
		m.addAttribute("contextPath", context.getContextPath());
		return "register";
	}
	
	@Autowired
	private HttpServletRequest context;
}
