package org.crowdguru.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
public class StaticPageController {
	
	private HttpServletRequest httpServletRequest;
	
	@Autowired
	public StaticPageController( HttpServletRequest httpServletRequest) {
		this.httpServletRequest = httpServletRequest;
	}
	
	@RequestMapping("/pages/{pageName}")
	public String index(@PathVariable String pageName, Model model) {
		return "pages/" + pageName;
	}
	
	@ModelAttribute
	public void populateModel(Model model) {
		model.addAttribute("contextPath", httpServletRequest.getContextPath());
	}
}
