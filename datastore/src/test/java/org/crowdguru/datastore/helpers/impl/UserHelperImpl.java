package org.crowdguru.datastore.helpers.impl;

import org.crowdguru.datastore.constants.UserConstants;
import org.crowdguru.datastore.domain.User;
import org.crowdguru.datastore.helpers.UserHelper;

public class UserHelperImpl implements UserHelper, UserConstants{

	@Override
	public User user1() {
		User user = new User();
		user.setForename(USER_1_FORENAME);
		user.setSurname(USER_1_SURNAME);
		user.setAboutMe(USER_1_ABOUTME);
		user.setHomeTown(USER_1_HOMETOWN);
		user.setProviderId(USER_1_PROVIDER_ID);
		user.setProviderUserId(USER_1_PROVIDER_USERID);
		return user;
	}
	
	@Override
	public User user2() {
		User user = new User();
		user.setForename(USER_2_FORENAME);
		user.setSurname(USER_2_SURNAME);
		user.setAboutMe(USER_2_ABOUTME);
		user.setHomeTown(USER_2_HOMETOWN);
		user.setProviderId(USER_2_PROVIDER_ID);
		user.setProviderUserId(USER_2_PROVIDER_USERID);
		return user;
	}
	
	@Override
	public User user3() {
		User user = new User();
		user.setForename(USER_3_FORENAME);
		user.setSurname(USER_3_SURNAME);
		user.setAboutMe(USER_3_ABOUTME);
		user.setHomeTown(USER_3_HOMETOWN);
		user.setProviderId(USER_3_PROVIDER_ID);
		user.setProviderUserId(USER_3_PROVIDER_USERID);
		return user;
	}

}
