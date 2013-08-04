package org.crowdguru.webapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.crowdguru.datastore.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SkeletalController {

	@RequestMapping("/gurus")
	public @ResponseBody List<User> allGurus() { 
		return new ArrayList<User>();
	}
}
