package org.crowdguru.datastore.context;

import javax.sql.DataSource;

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
public class MysqlDataSourceCreatorTest{
	
	@InjectMocks
	MysqlDataSourceCreator cut;
	
	@Mock
	Environment env;
	
	@Test
	public void createsDataSource() {
		when(env.getProperty("datastore.driver")).thenReturn("com.mysql.jdbc.Driver");
		when(env.getProperty("datastore.name")).thenReturn("crowdguru");
		when(env.getProperty("datastore.password")).thenReturn("crowdguru");
		when(env.getProperty("datastore.url")).thenReturn("jdbc:mysql://localhost/crowdguru");
		DataSource source = cut.create();
		assertThat(source, notNullValue());
	}
}	
