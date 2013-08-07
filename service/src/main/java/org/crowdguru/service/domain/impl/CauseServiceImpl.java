package org.crowdguru.service.domain.impl;

import org.crowdguru.datastore.domain.Cause;
import org.crowdguru.service.domain.CauseService;
import org.springframework.context.annotation.Profile;

@Profile({"cloud, prod, dev"})
public class CauseServiceImpl implements CauseService {

	public CauseServiceImpl() {
		log().warn("state=created");
	}
	
	@Override
	public Cause save(Cause cause) {
		// TODO Auto-generated method stub
		return cause;
	}

	@Override
	public Cause findOne(Long id) {
		// TODO Auto-generated method stub
		return new Cause();
	}

}
