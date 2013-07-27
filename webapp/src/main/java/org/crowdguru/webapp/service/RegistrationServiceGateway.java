package org.crowdguru.webapp.service;

import java.util.List;

import org.crowdguru.datastore.domain.Sector;
import org.crowdguru.datastore.domain.SkillGroup;
import org.crowdguru.service.domain.RegistrationService;
import org.crowdguru.service.request.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceGateway {
	
	private RegistrationService registrationService;
	
	@Autowired
	public RegistrationServiceGateway(RegistrationService registrationService) {
		this.registrationService = registrationService;
	}

	public void register(RegistrationRequest request) {
		registrationService.register(request);
	}
	
	public List<SkillGroup> getSkillGroups(){
		return registrationService.getSkillGroups();
	}

	public List<Sector> getSectorGroups() {
		return registrationService.getSectors();
	}
}
