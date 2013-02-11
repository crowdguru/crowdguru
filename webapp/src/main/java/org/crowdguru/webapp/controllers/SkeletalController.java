package org.crowdguru.webapp.controllers;

import java.util.List;

import org.crowdguru.datastore.domain.Guru;
import org.crowdguru.datastore.repositories.GuruRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SkeletalController {

	@RequestMapping("/gurus")
	public @ResponseBody List<Guru> allGurus() {
		return guruRepository.findAll();
	}


	@Autowired
	private GuruRepository guruRepository;
}
