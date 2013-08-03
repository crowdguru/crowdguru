package org.crowdguru.datastore.context;

import javax.sql.DataSource;

import org.cloudfoundry.runtime.env.RdbmsServiceInfo;
import org.cloudfoundry.runtime.service.relational.RdbmsServiceCreator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.env.Environment;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(MockitoJUnitRunner.class)
public class CloudFoundryfDataSourceCreatorTest{

	@InjectMocks
	CloudFoundryfDataSourceCreator cut;
	
	@Mock
	CloudFoundryEnvironmentGateway cloudFoundryEnvironment;
	
	@Mock
	Environment env;
	
	@Test
	public void initializeWithProfile() {
		
		when(env.getProperty("cf.mysql.servicename")).thenReturn("crowdgurudb");
		RdbmsServiceInfo info = mock(RdbmsServiceInfo.class);
		when(cloudFoundryEnvironment.getRdbmsServiceInfo("crowdgurudb")).thenReturn(info);
		
		when(info.getUrl()).thenReturn("jdbc:mysql://localhost/crowdguru");
		when(info.getUserName()).thenReturn("crowdguru");
		when(info.getPassword()).thenReturn("crowdguru");
		DataSource source = cut.create();
		assertThat(source, notNullValue());
	}
}
