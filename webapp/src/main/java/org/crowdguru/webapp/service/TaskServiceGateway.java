package org.crowdguru.webapp.service;

import org.crowdguru.datastore.domain.SkillGroup;
import org.crowdguru.datastore.domain.Task;
import org.crowdguru.service.domain.TaskService;
import org.crowdguru.service.request.CreateTaskRequest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceGateway {
	
	private TaskService taskService;
	
	public TaskServiceGateway(){
		log().warn("activity=created");
	}
	
	@Autowired
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}
	
	public Task create(CreateTaskRequest request){
		return taskService.create(request);
	}

	public List<SkillGroup> getSkillGroups() {
		return taskService.getSkillGroups();
	}

	public List<Task> getTasks() {
		return taskService.getTasks();
	}
}
