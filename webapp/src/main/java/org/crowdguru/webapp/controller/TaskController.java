package org.crowdguru.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/tasks")
public class TaskController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String getTasks(Model m) {
		log().info("activity=getTasks");
		return "tasks/list";
	}
	
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public String createTask(Model m) {
		log().info("activity=createTask");
		return "tasks/new";
	}
	
	@RequestMapping(value="/publish", method=RequestMethod.POST)
	public @ResponseBody String publishTask(Model m) {
		log().info("activity=publishTask");
		return "done";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public @ResponseBody String saveTask(Model m) {
		log().info("activity=saveTask");
		return "done";
	}
	
	@RequestMapping(value="/{taskId}", method=RequestMethod.GET)
	public String getTask(@PathVariable String taskId, Model m) {
		log().info("activity=getTask;id=" + taskId);
		return "tasks/show";
	}
	
}
