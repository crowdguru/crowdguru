package org.crowdguru.service.domain.impl;

import java.util.List;

import org.crowdguru.datastore.domain.Sector;
import org.crowdguru.datastore.domain.SkillGroup;
import org.crowdguru.service.domain.RegistrationService;
import org.crowdguru.service.request.RegistrationRequest;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	@Override
	public void register(RegistrationRequest request) {
		log().warn("state=init");
	}
	
	@Override
	public List<SkillGroup> getSkillGroups() {
		log().debug("> getSkillGroups");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Sector> getSectors() {
		log().debug("> getSectors");
		// TODO Auto-generated method stub
		return null;
	}
}
