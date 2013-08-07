package org.crowdguru.service.gateway;

import java.util.List;

import org.crowdguru.datastore.domain.SkillGroup;
import org.crowdguru.datastore.repositories.SkillGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class SkillGroupRepositoryGateway {

	private SkillGroupRepository skillGroupRepository;
	
	@Autowired
	public void setSkillRepository(SkillGroupRepository skillGroupRepository) {
		this.skillGroupRepository = skillGroupRepository;
	}
	
	public List<SkillGroup> findAll(){
		return skillGroupRepository.findAll();
	}

}
