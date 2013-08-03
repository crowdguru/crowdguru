package org.crowdguru.service.domain.impl;

import java.util.List;

import org.crowdguru.datastore.domain.SkillGroup;
import org.crowdguru.service.domain.SkillGroupService;
import org.crowdguru.service.gateway.SkillGroupRepositoryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;

@Profile({"cloud, prod, dev"})
public class SkillGroupServiceImpl implements SkillGroupService {

	private SkillGroupRepositoryGateway skillGroupRepository;
	
	@Autowired
	public void setSkillGroupRepository(SkillGroupRepositoryGateway skillGroupRepositoryGateway) {
		this.skillGroupRepository = skillGroupRepositoryGateway;
	}
	
	@Override
	public List<SkillGroup> findAll() {
		return this.skillGroupRepository.findAll();
	}

}
