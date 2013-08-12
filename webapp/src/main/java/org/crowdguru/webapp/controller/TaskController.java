package org.crowdguru.webapp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.crowdguru.datastore.domain.SkillGroup;
import org.crowdguru.datastore.domain.Task;
import org.crowdguru.service.request.CreateTaskRequest;
import org.crowdguru.service.request.RegistrationRequest;
import org.crowdguru.webapp.service.RegistrationServiceGateway;
import org.crowdguru.webapp.service.TaskServiceGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/tasks")
public class TaskController {
	
	private TaskServiceGateway taskService;

	public TaskController() {
		log().warn("activity=created");
	}
	
	@Autowired
	public void setTaskService(TaskServiceGateway taskService) {
		this.taskService = taskService;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String onGetTasks(Model model) {
		log().info("activity=getTasks");
		List<Task> allTasks = taskService.getTasks();
		model.addAttribute("tasks", allTasks);
		return "tasks/list";
	}

	@RequestMapping(value="/new", method=RequestMethod.GET)
	public String onNewTask(Model model) {
		log().info("activity=createTask");
		List<SkillGroup> skillGroups = taskService.getSkillGroups();
		model.addAttribute("skillGroups", skillGroups);
		return "tasks/new";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody String onPostTask(@ModelAttribute CreateTaskRequest formData, 
			BindingResult bindingResult, @RequestParam MultipartFile photo) throws IOException {
		log().info("activity=createTask");
		String response = "failed";
		
		if(bindingResult.getErrorCount() == 1 && bindingResult.hasFieldErrors("photo")){
			formData.setPhoto(photo.getBytes());
			Task task = taskService.create(formData);
			response = task.getId().toString();
		}
		
		return response;
	}
	
	@RequestMapping(value="/{taskId}", method=RequestMethod.GET)
	public String onGetTask(@PathVariable String taskId, Model m) {
		log().info("activity=getTask;id=" + taskId);
		return "tasks/show";
	}
	
}
