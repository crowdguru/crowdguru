package org.crowdguru.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/gurus")
public class GuruController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String users(Model m) {
		return "users/list";
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String user(@PathVariable Long id, Model m) {
		return "users/show";
	}
}
