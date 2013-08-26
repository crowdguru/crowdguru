package org.crowdguru.webapp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.crowdguru.datastore.domain.Cause;
import org.crowdguru.datastore.domain.Offer;
import org.crowdguru.datastore.domain.SkillGroup;
import org.crowdguru.datastore.domain.Task;
import org.crowdguru.datastore.domain.User;
import org.crowdguru.service.request.CreateTaskRequest;
import org.crowdguru.service.request.OfferRequest;
import org.crowdguru.service.request.RegistrationRequest;
import org.crowdguru.webapp.security.SecurityAccessor;
import org.crowdguru.webapp.security.UserDetails;
import org.crowdguru.webapp.service.OfferServiceGateway;
import org.crowdguru.webapp.service.RegistrationServiceGateway;
import org.crowdguru.webapp.service.TaskServiceGateway;
import org.crowdguru.webapp.service.UserServiceGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/tasks")
public class TaskController {
	
	private TaskServiceGateway taskService;
	
	private OfferServiceGateway offerService;
	
	private SecurityAccessor securityAccessor;
	
	private UserServiceGateway userService;

	public TaskController() {
		log().warn("activity=created");
	}
	
	@Autowired
	public void setTaskService(TaskServiceGateway taskService) {
		this.taskService = taskService;
	}
	
	@Autowired
	public void setOfferService(OfferServiceGateway offerService) {
		this.offerService = offerService;
	}
	
	@Autowired
	public void setSecurityAccessor(SecurityAccessor securityAccessor){
		this.securityAccessor = securityAccessor;
	}
	
	@Autowired
	public void setUserServiceGateway(UserServiceGateway userService){
		this.userService = userService;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String getTasks(Model model) {
		log().info("activity=getTasks");
		List<Task> allTasks = taskService.getTasks();
		model.addAttribute("tasks", allTasks);
		return "tasks/list";
	}

	@RequestMapping(value="/new", method=RequestMethod.GET)
	public String newTask(Model model) {
		log().info("activity=createTask");
		List<SkillGroup> skillGroups = taskService.getSkillGroups();
		model.addAttribute("skillGroups", skillGroups);
		return "tasks/new";
	}
	
	@RequestMapping(method=RequestMethod.POST, produces="application/json")
	public String createTask(@ModelAttribute CreateTaskRequest request, 
			BindingResult bindingResult, @RequestParam MultipartFile photo,
			Model model) throws IOException {
		
		if(bindingResult.getErrorCount() == 1 && bindingResult.hasFieldErrors("photo")){
			String email = securityAccessor.getCurrentUserEmail();
			User keyContact = userService.getUserByEmail(email);
			request.setPhoto(photo.getBytes());
			request.setKeyContactEmail(email);
			Set<Cause> causes = keyContact.getCauses();
			request.setCauseId(causes.iterator().next().getId());
			Task task = taskService.create(request);
			model.addAttribute("stat", task.getId().toString());
		}
		
		return "jsonView";
	}
	
	@RequestMapping(value="/{taskId}", method=RequestMethod.GET)
	public String getTask(@PathVariable Long taskId, Model model) {
		Task task = taskService.getTaskById(taskId);
		model.addAttribute("task", task);
		return "tasks/show";
	}
	
	@RequestMapping(value="/{id}/offerHelp", method=RequestMethod.POST, consumes="application/json", produces="application/json")
	public String offerHelp(@PathVariable Long id, UserDetails userDetails, @RequestBody OfferRequest request, Model model) {
		request.setTaskId(id);
		request.setOwner(userDetails.getUsername());
		Offer offer = offerService.create(request);
		model.addAttribute("stat", "OK");
		return "jsonView";
	}
}
