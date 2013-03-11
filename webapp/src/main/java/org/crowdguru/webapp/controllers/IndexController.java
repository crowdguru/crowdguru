package org.crowdguru.webapp.controllers;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
public class IndexController {
	
	@RequestMapping("/")
	public String index(Model m) {
		log().info("Context path '" + contextPath + "'");
		m.addAttribute("contextPath", StringUtils.isNotBlank(contextPath) ? contextPath : "/");
		return "index";
	}
	
	@Value("${context.path}")
	private String contextPath;
}
