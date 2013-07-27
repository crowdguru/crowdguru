package org.crowdguru.service.domain.impl;

import org.crowdguru.datastore.domain.Guru;
import org.crowdguru.service.domain.GuruService;
import org.springframework.stereotype.Service;

@Service
public class GuruServiceImpl implements GuruService{

	public GuruServiceImpl() {
		log().warn("state=init");
	}
	
	@Override
	public Guru save(Guru guru) {
		// TODO Auto-generated method stub
		return guru;
	}

}
