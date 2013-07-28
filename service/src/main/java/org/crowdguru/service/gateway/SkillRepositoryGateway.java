package org.crowdguru.service.gateway;

import org.crowdguru.datastore.domain.Skill;
import org.crowdguru.datastore.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class SkillRepositoryGateway {

	private SkillRepository skillRepository;
	
	@Autowired
	public void setSkillRepository(SkillRepository skillRepository) {
		this.skillRepository = skillRepository;
	}

	public Skill save(Skill skill) {
		return skillRepository.save(skill);
	}

	public Skill findOne(Long id) {
		return skillRepository.findOne(id);
	}
}
