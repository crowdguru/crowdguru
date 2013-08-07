package org.crowdguru.service.domain.stub;

import org.crowdguru.datastore.domain.Cause;
import org.crowdguru.service.domain.CauseService;

public class TestCauseService implements CauseService {
	TestRepository<Cause> repository;
	
	public TestCauseService() {
		log().warn("state=created");
		repository = new TestRepository<Cause>();
	}

	@Override
	public Cause save(Cause cause) {
		return repository.save(cause);
	}

	@Override
	public Cause findOne(Long id) {
		return repository.findOne(id);
	}

}
