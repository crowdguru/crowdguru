package org.crowdguru.service.domain.impl;

import org.crowdguru.datastore.repositories.UserRepository;
import org.crowdguru.service.domain.RegistrationService;
import org.crowdguru.service.request.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	@Override
	public void register(RegistrationRequest request) {
		log().warn("Real implementation active");
	}
	
	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args) {
		System.out.print("Merhaba");
	}
}
