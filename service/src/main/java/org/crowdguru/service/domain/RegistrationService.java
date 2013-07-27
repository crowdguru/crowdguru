package org.crowdguru.service.domain;

import java.util.List;

import org.crowdguru.datastore.domain.Sector;
import org.crowdguru.datastore.domain.SkillGroup;
import org.crowdguru.service.request.RegistrationRequest;

public interface RegistrationService {

	public void register(RegistrationRequest request);
	
	public List<SkillGroup> getSkillGroups();

	public List<Sector> getSectors();
}
