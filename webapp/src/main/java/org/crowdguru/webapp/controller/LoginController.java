package org.crowdguru.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
public class LoginController {
	
	@RequestMapping("/login")
	public String index(Model m) {
		m.addAttribute("contextPath", context.getContextPath());
		return "signin";
	}
	
	@Autowired
	private HttpServletRequest context;
}
