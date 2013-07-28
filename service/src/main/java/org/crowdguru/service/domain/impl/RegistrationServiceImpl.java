package org.crowdguru.service.domain.impl;

import org.crowdguru.datastore.domain.User;
import org.crowdguru.datastore.domain.User.Type;
import org.crowdguru.service.domain.EncoderService;
import org.crowdguru.service.domain.RegistrationService;
import org.crowdguru.service.domain.UserService;
import org.crowdguru.service.exception.InvalidAccountTypeException;
import org.crowdguru.service.request.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;

public class RegistrationServiceImpl implements RegistrationService {
	
	private UserService userService; 
	
	private EncoderService encoderService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setEncoderService(EncoderService encoderService) {
		this.encoderService = encoderService;
	}
	
	public RegistrationServiceImpl() {
		log().warn("state=created");	
	}

	@Override
	public User register(RegistrationRequest request) throws InvalidAccountTypeException{
		log().warn("activity=register");
		User user = new User();
		user.setEmail(request.getEmail());
		user.setForename(request.getForename());
		user.setSurname(request.getSurname());
		user.setPassword(encodePassword(request.getPassword()));
		user.setType(determineType(request));
		return userService.save(user);
	}
	
	private String encodePassword(String rawPassword) {
		return encoderService.encode(rawPassword);
	}
	
	private User.Type determineType(RegistrationRequest request) throws InvalidAccountTypeException{
		User.Type type = Type.GURU;
		if(request.isGuru() && request.isKeyContact()) {
			type = type.BOTH;
		}else if(request.isGuru()) {
			type = type.GURU;
		}else if(request.isKeyContact()) {
			type = type.KEYCONTACT;
		}else {
			throw new InvalidAccountTypeException("Account type not specified");
		}
		
		return type;
	}
}
