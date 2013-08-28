package org.crowdguru.service.domain.impl;

import java.util.List;

import org.crowdguru.datastore.domain.Cause;
import org.crowdguru.datastore.domain.SkillGroup;
import org.crowdguru.datastore.domain.Task;
import org.crowdguru.datastore.domain.User;
import org.crowdguru.service.domain.CauseService;
import org.crowdguru.service.domain.SkillGroupService;
import org.crowdguru.service.domain.TaskService;
import org.crowdguru.service.domain.UserService;
import org.crowdguru.service.gateway.TaskRepositoryGateway;
import org.crowdguru.service.request.CreateTaskRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class TaskServiceImpl implements TaskService {

	private SkillGroupService skillGroupService;
	
	private TaskRepositoryGateway taskRepository;
	
	private UserService userService;
	
	private CauseService causeService;
	
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
	
	@Autowired
	public void setUserService(UserService userService){
		this.userService = userService;
	}
	
	@Autowired
	public void setCauseService(CauseService causeService){
		this.causeService = causeService;
	}
	
	@Override
	@Transactional
	public Task create(CreateTaskRequest request) {
		Task task = new Task();
		task.setTitle(request.getTitle());
		task.setShortDescription(request.getShortDescription());
		User owner = userService.getUserByEmail(request.getkeyContactEmail());
		task.setOwner(owner);
		Cause cause = causeService.getCauseById(request.getCauseId());
		task.setCause(cause);
		task = taskRepository.save(task);
		log().info("activity=create"+ ";id=" + task.getId() + ";title=" + request.getTitle() + ";owner=" + owner.getId() + ";cause=" + cause.getId());
		return task;
	}

	@Override
	@Transactional(readOnly=true)
	public List<SkillGroup> getSkillGroups() {
		return skillGroupService.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Task> getTasks() {
		return taskRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly=true)
	public Task getTaskById(Long id){
		Task task = taskRepository.findOne(id);
		log().info("activity=getTask;id=" + id + ";result=" + (task == null ? "notfound" : "found"));
		return task;
	}
}
