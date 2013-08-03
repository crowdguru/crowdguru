package org.crowdguru.service.domain;

import java.util.List;

import org.crowdguru.datastore.domain.SkillGroup;

public interface SkillGroupService {

	public List<SkillGroup> findAll();
}
