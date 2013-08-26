package org.crowdguru.service.domain.impl;

import java.util.List;

import org.crowdguru.datastore.domain.Cause;
import org.crowdguru.service.domain.CauseService;
import org.crowdguru.service.gateway.CauseRepositoryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

public class CauseServiceImpl implements CauseService {
	
	private CauseRepositoryGateway causeRepository;
	
	@Autowired
	public void setCauseRepository(CauseRepositoryGateway causeRepositoryGateway){
		this.causeRepository = causeRepositoryGateway;
	}

	public CauseServiceImpl() {
		log().info("activity=create");
	}
	
	@Override
	@Transactional
	public Cause save(Cause cause) {
		Cause savedCause = causeRepository.save(cause);
		log().info("activity=save;id=" + savedCause.getId() + ";name=" + savedCause.getName());
		return savedCause;
	}

	@Override
	@Transactional(readOnly=true)
	public Cause getCauseById(Long id) {
		Cause cause = causeRepository.findOne(id);
		log().info("activity=getCauseById;id=" + id + ";found=" + (cause != null));
		return cause;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Cause> getCauses() {
		List<Cause> findings = causeRepository.findAll();
		log().info("activity=findAll;found=" + findings.size());
		return findings;
	}
	
}
