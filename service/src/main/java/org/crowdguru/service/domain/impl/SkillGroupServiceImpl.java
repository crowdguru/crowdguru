package org.crowdguru.service.domain.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.crowdguru.datastore.domain.Skill;
import org.crowdguru.datastore.domain.SkillGroup;
import org.crowdguru.service.domain.SkillGroupService;
import org.crowdguru.service.gateway.SkillGroupRepositoryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;

public class SkillGroupServiceImpl implements SkillGroupService {

	private SkillGroupRepositoryGateway skillGroupRepository;
	
	public SkillGroupServiceImpl(){
		log().warn("activity=created");
	}
	
	@Autowired
	public void setSkillGroupRepository(SkillGroupRepositoryGateway skillGroupRepositoryGateway) {
		this.skillGroupRepository = skillGroupRepositoryGateway;
	}
	
	@Override
	public List<SkillGroup> findAll() {
		return skillGroupRepository.findAll();
	}
}
