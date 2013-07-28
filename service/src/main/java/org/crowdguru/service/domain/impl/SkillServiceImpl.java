package org.crowdguru.service.domain.impl;

import org.crowdguru.datastore.domain.Skill;
import org.crowdguru.service.domain.SkillService;
import org.crowdguru.service.gateway.SkillRepositoryGateway;
import org.springframework.context.annotation.Profile;

@Profile({"cloud, prod, dev"})
public class SkillServiceImpl implements SkillService {

	private SkillRepositoryGateway skillRepository;
	
	public void setSkillRepository(SkillRepositoryGateway skillRepositoryGateway) {
		this.skillRepository = skillRepositoryGateway;
	}
	
	@Override
	public Skill findOne(Long id) {
		return skillRepository.findOne(id);
	}

	@Override
	public Skill save(Skill skill) {
		return skillRepository.save(skill);
	}

}
