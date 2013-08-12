package org.crowdguru.service.domain;

import java.util.List;

import org.crowdguru.datastore.domain.SkillGroup;
import org.crowdguru.datastore.domain.Task;
import org.crowdguru.service.request.CreateTaskRequest;

public interface TaskService {

	public Task create(CreateTaskRequest request);

	public List<SkillGroup> getSkillGroups();

	public List<Task> getTasks();
}
