package org.crowdguru.service.domain;

import org.crowdguru.datastore.domain.Skill;

public interface SkillService {
	
	public Skill findOne(Long id);
}
