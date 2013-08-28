package org.crowdguru.webapp.controller;

import javax.servlet.http.HttpSession;

import org.crowdguru.datastore.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
public class IndexController {

	@RequestMapping("/")
	public String index(HttpSession session, Model model) {
		return "index";
	}
}
