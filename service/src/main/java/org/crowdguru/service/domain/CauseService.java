package org.crowdguru.service.domain;

import java.util.List;

import org.crowdguru.datastore.domain.Cause;

public interface CauseService {
	
	public Cause save(Cause cause);

	public Cause findOne(Long id);
	
	public List<Cause> findAll();
	
}
