package org.crowdguru.datastore.helpers.impl;

import org.crowdguru.datastore.constants.UserConstants;
import org.crowdguru.datastore.domain.User;
import org.crowdguru.datastore.domain.User.Type;
import org.crowdguru.datastore.helpers.UserHelper;

public class UserHelperImpl implements UserHelper, UserConstants{

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
		return user;
	}

}
