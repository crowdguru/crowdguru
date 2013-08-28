package org.crowdguru.datastore.helpers.impl;

import java.util.Collections;

import org.crowdguru.datastore.constants.CauseConstraints;
import org.crowdguru.datastore.domain.Cause;
import org.crowdguru.datastore.domain.Task;
import org.crowdguru.datastore.domain.User;
import org.crowdguru.datastore.helpers.CauseHelper;
import org.crowdguru.datastore.repositories.CauseRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CauseHelperImpl implements CauseHelper, CauseConstraints{

	@Autowired
	private CauseRepository causeRepository;
	
	@Override
	public Cause cause1() {
		Cause cause = new Cause();
		cause.setName(CAUSE_1_NAME);
		return cause;
	}

	@Override
	public void removeRelationships() {
		for(Cause cause: causeRepository.findAll()){
			cause.setKeyContacts(Collections.<User>emptySet());
			cause.setTasks(Collections.<Task>emptySet());
			causeRepository.save(cause);
		}
	}
}
