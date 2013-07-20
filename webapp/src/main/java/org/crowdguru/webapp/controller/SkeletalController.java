package org.crowdguru.webapp.controller;

import java.util.List;

import org.crowdguru.datastore.domain.User;
import org.crowdguru.datastore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SkeletalController {

	@RequestMapping("/gurus")
	public @ResponseBody List<User> allGurus() { 
		return guruRepository.findAll();
	}


	@Autowired
	private UserRepository guruRepository;
}
