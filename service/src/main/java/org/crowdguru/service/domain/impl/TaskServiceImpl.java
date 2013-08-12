package org.crowdguru.service.domain.impl;

import java.util.ArrayList;
import java.util.List;
import org.crowdguru.datastore.domain.SkillGroup;
import org.crowdguru.datastore.domain.Task;
import org.crowdguru.service.domain.SkillGroupService;
import org.crowdguru.service.domain.TaskService;
import org.crowdguru.service.gateway.TaskRepositoryGateway;
import org.crowdguru.service.request.CreateTaskRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class TaskServiceImpl implements TaskService {

	SkillGroupService skillGroupService;
	
	TaskRepositoryGateway taskRepository;
	
	public TaskServiceImpl(){
		log().warn("activity=created");
	}
	
	@Autowired
	public void setSkillGroupService(TaskRepositoryGateway taskRepositoryGateway){
		this.taskRepository = taskRepositoryGateway;
	}
	
	@Autowired
	public void setSkillGroupService(SkillGroupService skillGroupService){
		this.skillGroupService = skillGroupService;
	}
	
	@Override
	@Transactional
	public Task create(CreateTaskRequest request) {
		log().warn("activity=create");
		Task task = new Task();
		task.setTitle(request.getTitle());
		task.setShortDescription(request.getShortDescription());
		return taskRepository.save(task);
	}

	@Override
	@Transactional
	public List<SkillGroup> getSkillGroups() {
		return skillGroupService.findAll();
	}

	@Override
	@Transactional
	public List<Task> getTasks() {
		return taskRepository.findAll();
	}
	
//	@Override
//	@Transactional
//	public List<Task> getTasks() {
//		List<Task> tasks = new ArrayList<Task>();
//		
//		for(int i = 1; i <= 12; i++){
//			tasks.add(createTask(i));
//		}
//		
//		return tasks;
//	}
//	
//	private Task createTask(Integer id){
//		Task task = new Task();
//		task.setId(new Long(id));
//		task.setTitle("Title " + id.toString());
//		task.setShortDescription("Short description " + id.toString());
//		return task;
//	}
	
}
