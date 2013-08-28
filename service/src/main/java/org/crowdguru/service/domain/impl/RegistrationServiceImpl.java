package org.crowdguru.service.domain.impl;

import java.util.HashSet;
import java.util.Set;

import org.crowdguru.datastore.domain.Cause;
import org.crowdguru.datastore.domain.User;
import org.crowdguru.datastore.domain.User.Type;
import org.crowdguru.service.domain.CauseService;
import org.crowdguru.service.domain.RegistrationService;
import org.crowdguru.service.domain.UserService;
import org.crowdguru.service.exception.InvalidAccountTypeException;
import org.crowdguru.service.request.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;

public class RegistrationServiceImpl implements RegistrationService {
	
	private UserService userService; 
	
	private CauseService causeService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setCauseService(CauseService causeService){
		this.causeService = causeService;
	}
	
	public RegistrationServiceImpl() {
		log().warn("activity=create");	
	}

	@Override
	public User register(RegistrationRequest request) throws InvalidAccountTypeException{
		log().warn("activity=register");
		User user = createUser(request);
		user = processTypeSpecificFields(user, request);
		log().info("activity=register;id=" + user.getId() + ";email=" + user.getEmail());
		return user;
	}

	private User createUser(RegistrationRequest request) throws InvalidAccountTypeException {
		User user = new User();
		user.setEmail(request.getEmail());
		user.setForename(request.getForename());
		user.setSurname(request.getSurname());
		user.setPassword(request.getPassword());
		user.setType(determineType(request));
		user.setLocation(request.getLocation());
		return userService.save(user);
	}

	private User processTypeSpecificFields(User user, RegistrationRequest request) {
		if(request.isKeyContact()){
			user = processKeyContactFields(user, request);
			
		}else if(request.isGuru()){
			user = processGuruFields(user, request);
		}
		
		return user;
	}

	private User processKeyContactFields(User user, RegistrationRequest request) {
		Cause cause = saveCause(request, user);
		Set<Cause> causes = new HashSet<Cause>();
		causes.add(cause);
		user.setCauses(causes);
		return userService.save(user);
	}
	
	private User processGuruFields(User user, RegistrationRequest request) {
		user.setShortProfile(request.getShortProfile());
		return userService.save(user);
	}

	private Cause saveCause(RegistrationRequest request, User user) {
		Cause cause = new Cause();
		cause.setName(request.getCauseName());
		Set<User> keyContacts = new HashSet<User>();
		keyContacts.add(user);
		cause.setKeyContacts(keyContacts);
		return causeService.save(cause);
	}

	private User.Type determineType(RegistrationRequest request) throws InvalidAccountTypeException{
		User.Type type = Type.GURU;
		if(request.isGuru() && request.isKeyContact()) {
			type = Type.BOTH;
		}else if(request.isGuru()) {
			type = Type.GURU;
		}else if(request.isKeyContact()) {
			type = Type.KEYCONTACT;
		}else {
			throw new InvalidAccountTypeException("Account type not specified");
		}
		
		return type;
	}
}
