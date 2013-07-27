package org.crowdguru.service.domain.impl;

import org.crowdguru.datastore.domain.KeyContact;
import org.crowdguru.service.domain.KeyContactService;
import org.springframework.stereotype.Service;

@Service
public class KeyContactServiceImpl implements KeyContactService{

	public KeyContactServiceImpl() {
		log().warn("state=init");
	}
	
	@Override
	public KeyContact save(KeyContact keyContact) {
		// TODO Auto-generated method stub
		return keyContact;
	}
}
