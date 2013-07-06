package org.crowdguru.datastore.validators.impl;


import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.crowdguru.datastore.constants.UserConstants;
import org.crowdguru.datastore.domain.User;
import org.crowdguru.datastore.validators.UserValidator;

public class UserValidatorImpl implements UserValidator, UserConstants {

	@Override
	public void validateUser1(User user) {
		assertThat(user, is(notNullValue()));
		assertThat("forename", user.getForename(), is(equalTo(USER_1_FORENAME)));
		assertThat("surname", user.getSurname(), is(equalTo(USER_1_SURNAME)));
		assertThat("home town", user.getHomeTown(), is(equalTo(USER_1_HOMETOWN)));
		assertThat("about me", user.getAboutMe(), is(equalTo(USER_1_ABOUTME)));
		assertThat("provider id", user.getProviderId(), is(equalTo(USER_1_PROVIDER_ID)));
		assertThat("provider user id", user.getProviderUserId(), is(equalTo(USER_1_PROVIDER_USERID)));
	}
	
	@Override
	public void validateUser2(User user) {
		assertThat(user, is(notNullValue()));
		assertThat("forename", user.getForename(), is(equalTo(USER_2_FORENAME)));
		assertThat("surname", user.getSurname(), is(equalTo(USER_2_SURNAME)));
		assertThat("home town", user.getHomeTown(), is(equalTo(USER_2_HOMETOWN)));
		assertThat("about me", user.getAboutMe(), is(equalTo(USER_2_ABOUTME)));
		assertThat("provider id", user.getProviderId(), is(equalTo(USER_2_PROVIDER_ID)));
		assertThat("provider user id", user.getProviderUserId(), is(equalTo(USER_2_PROVIDER_USERID)));
	}

}
