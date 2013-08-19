package org.crowdguru.webapp.controller;

import org.crowdguru.datastore.domain.User;
import org.crowdguru.service.domain.UserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/profile")

public class ProfileController {

	@RequestMapping(method=RequestMethod.GET)
	public String showProfile(Model model) {
		model.addAttribute("user", getUser());
		return "profile";
	}
	
	private User getUser() {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userDetails.getUser();
	}
}
