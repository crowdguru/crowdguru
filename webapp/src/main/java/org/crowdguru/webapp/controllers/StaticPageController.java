package org.crowdguru.webapp.controllers;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
public class StaticPageController {
	
	/* Very basic controller to map anything on the /pages path to a static placeholder page.
	 * TODO: Extend this later on to match the path after /pages to a specific static page, e.g.
	 * '/pages/about' would simply resolve to '/views/pages/about.html'
	 */
	
	@RequestMapping("/pages/**")
	public String index(Model m) {
		log().info("Context path '" + contextPath + "'");
		m.addAttribute("contextPath", StringUtils.isNotBlank(contextPath) ? contextPath : "/");
		return "pages/default";
	}
	
	@Value("${context.path}")
	private String contextPath;
}
