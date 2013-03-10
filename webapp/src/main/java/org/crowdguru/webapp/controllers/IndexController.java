package org.crowdguru.webapp.controllers;

//import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
public class IndexController {
	
	@RequestMapping("/")
	public String index(Model m) {
		//m.addAttribute("contextPath", request.getContextPath());
		m.addAttribute("contextPath", "/crowdguru/");
		return "index";
	}
}
