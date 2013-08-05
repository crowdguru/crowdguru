package org.crowdguru.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
public class StaticPageController {
	
	@RequestMapping("/pages/{pageName}")
	public String index(@PathVariable String pageName, Model model) {
		return "pages/" + pageName;
	}
}
