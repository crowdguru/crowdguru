package org.crowdguru.service.gateway;

import java.util.List;

import org.crowdguru.datastore.domain.Task;
import org.crowdguru.datastore.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TaskRepositoryGateway {

	private TaskRepository taskRepository;
	
	@Autowired
	public void setTaskRepository(TaskRepository TaskRepository) {
		this.taskRepository = TaskRepository;
	}
	
	public List<Task> findAll(){
		return taskRepository.findAll();
	}
	
	public Task save(Task task){
		return taskRepository.save(task);
	}

}
