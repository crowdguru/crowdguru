package org.crowdguru.datastore.helpers.impl;

import java.util.Collections;

import org.crowdguru.datastore.constants.TaskConstraints;
import org.crowdguru.datastore.domain.Task;
import org.crowdguru.datastore.domain.User;
import org.crowdguru.datastore.helpers.TaskHelper;
import org.crowdguru.datastore.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TaskHelperImpl implements TaskHelper, TaskConstraints{

	@Autowired
	private TaskRepository taskRepository;
	
	@Override
	public Task task1() {
		Task task = new Task();
		task.setTitle(TASK_1_TITLE);
		task.setShortDescription(TASK_1_SHORT_DESCRIPTION);
		return task;
	}

	@Override
	public void removeRelationships() {
		for(Task task: taskRepository.findAll()){
			task.setAssignees(Collections.<User>emptySet());
			task.setCause(null);
			task.setOwner(null);
			taskRepository.save(task);
		}
	}
}
