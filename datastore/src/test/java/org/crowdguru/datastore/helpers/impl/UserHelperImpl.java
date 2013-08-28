package org.crowdguru.datastore.helpers.impl;

import java.util.Collections;

import org.crowdguru.datastore.constants.UserConstants;
import org.crowdguru.datastore.domain.Cause;
import org.crowdguru.datastore.domain.Task;
import org.crowdguru.datastore.domain.User;
import org.crowdguru.datastore.domain.User.Type;
import org.crowdguru.datastore.helpers.UserHelper;
import org.crowdguru.datastore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserHelperImpl implements UserHelper, UserConstants{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User user1() {
		User user = new User();
		user.setForename(USER_1_FORENAME);
		user.setSurname(USER_1_SURNAME);
		user.setShortProfile(USER_1_SHORTPROFILE);
		user.setLocation(USER_1_LOCATION);
		user.setProviderId(USER_1_PROVIDER_ID);
		user.setProviderUserId(USER_1_PROVIDER_USERID);
		user.setEmail(USER_1_EMAIL);
		user.setPassword(USER_1_PASSWORD);
		user.setType(USER_1_TYPE);
		return user;
	}
	
	@Override
	public User user2() {
		User user = new User();
		user.setForename(USER_2_FORENAME);
		user.setSurname(USER_2_SURNAME);
		user.setShortProfile(USER_2_SHORTPROFILE);
		user.setLocation(USER_2_LOCATION);
		user.setProviderId(USER_2_PROVIDER_ID);
		user.setProviderUserId(USER_2_PROVIDER_USERID);
		user.setEmail(USER_2_EMAIL);
		user.setPassword(USER_2_PASSWORD);
		user.setType(USER_2_TYPE);
		return user;
	}
	
	@Override
	public User user3() {
		User user = new User();
		user.setForename(USER_3_FORENAME);
		user.setSurname(USER_3_SURNAME);
		user.setShortProfile(USER_3_SHORTPROFILE);
		user.setLocation(USER_3_LOCATION);
		user.setProviderId(USER_3_PROVIDER_ID);
		user.setProviderUserId(USER_3_PROVIDER_USERID);
		user.setEmail(USER_3_EMAIL);
		user.setPassword(USER_3_PASSWORD);
		user.setType(USER_3_TYPE);
		return user;
	}

	@Override
	public void removeRelationships() {
		for(User user: userRepository.findAll()){
			user.setOwnedTasks(Collections.<Task>emptySet());
			user.setCauses(Collections.<Cause>emptySet());
			user.setAssignedTasks(Collections.<Task>emptySet());
			userRepository.save(user);	
		}
	}
}
