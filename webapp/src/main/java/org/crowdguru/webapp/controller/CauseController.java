package org.crowdguru.webapp.controller;

import java.util.List;

import org.crowdguru.datastore.domain.Cause;
import org.crowdguru.webapp.service.CauseServiceGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/causes")
public class CauseController {
	
	CauseServiceGateway causeService;
	
	@Autowired
	public void setCauseService(CauseServiceGateway causeServiceGateway){
		this.causeService = causeServiceGateway;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String users(Model model) {
		List<Cause> causes = causeService.findAll();
		model.addAttribute("causes", causes);
		return "causes/list";
	}
	
	@RequestMapping(value="/{causeId}", method=RequestMethod.GET)
	public String user(@PathVariable Long causeId, Model model) {
		Cause cause = causeService.findOne(causeId);
		model.addAttribute("causeDetails", cause);
		return "causes/show";
	}
}
