package org.crowdguru.webapp.builder;

import org.crowdguru.datastore.domain.User;
import org.crowdguru.webapp.builder.impl.ConnectionUserBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.facebook.api.Facebook;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ConnectionUserBuilderTest {

	private ConnectionUserBuilder cut;
	
	@Mock
	Connection<Facebook> mockConnection;
	
	private static final String PROVIDER_ID = "ID_1";
	
	private static final String PROVIDER_USER_ID = "ID_1";
	
	private static final String FIRST_NAME = "FORE_NAME_1";
	
	private static final String LAST_NAME = "LAST_NAME_1";
	
	@Test
	public void createsUser() {
		cut = new ConnectionUserBuilder();
		ConnectionKey key = new ConnectionKey(PROVIDER_ID, PROVIDER_USER_ID);
		UserProfile profile = mock(UserProfile.class);
		when(mockConnection.getKey()).thenReturn(key);
		when(mockConnection.fetchUserProfile()).thenReturn(profile);
		when(profile.getLastName()).thenReturn(LAST_NAME);
		when(profile.getFirstName()).thenReturn(FIRST_NAME);
		User user = cut.build(mockConnection);
		assertThat(user.getForename(), equalTo(FIRST_NAME));
		assertThat(user.getSurname(), equalTo(LAST_NAME));
		assertThat(user.getProviderId(), equalTo(PROVIDER_ID));
		assertThat(user.getProviderUserId(), equalTo(PROVIDER_USER_ID));
	}
}
