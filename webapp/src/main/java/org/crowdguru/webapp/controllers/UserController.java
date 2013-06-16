package org.crowdguru.webapp.controllers;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.crowdguru.datastore.domain.User;
import org.crowdguru.datastore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

@Controller
public class UserController {
	
	@RequestMapping("/users")
	public String users(Model m) {
		List<User> users = userRepository.findAll();
		m.addAttribute("contextPath", StringUtils.isNotBlank(contextPath) ? contextPath : "/");
		m.addAttribute("users", users);
		return "user/list";
	}
	
	@RequestMapping(value="/users/{userId}", method=RequestMethod.GET)
	public String user(@PathVariable Long userId, Model m) {
		User user = userRepository.findOne(userId);
		m.addAttribute("contextPath", StringUtils.isNotBlank(contextPath) ? contextPath : "/");
		m.addAttribute("user", user);
		return "user/profile";
	}

	@Autowired
	private UserRepository userRepository;
	
	@Value("${context.path}")
	private String contextPath;
}
