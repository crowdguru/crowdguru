package org.crowdguru.webapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.crowdguru.datastore.domain.User;
import org.crowdguru.datastore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

@Controller
public class UserController {
	
	@RequestMapping("/users")
	public String users(Model m) {
		List<User> users = new ArrayList<User>();
		m.addAttribute("contextPath", context.getContextPath());
		m.addAttribute("users", users);
		return "users/list";
	}
	
	@RequestMapping(value="/users/{userId}", method=RequestMethod.GET)
	public String user(@PathVariable Long userId, Model m) {
		User user = new User();
		m.addAttribute("contextPath", context.getContextPath());
		m.addAttribute("user", user);
		return "users/profile";
	}
	
	@Autowired
	private HttpServletRequest context;
}
