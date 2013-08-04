package org.crowdguru.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/profile")

public class ProfileController {

	@RequestMapping(method=RequestMethod.GET)
	public String showProfile(Model model) {
		return "profile";
	}
}
