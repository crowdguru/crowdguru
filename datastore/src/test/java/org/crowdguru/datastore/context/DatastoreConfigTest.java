package org.crowdguru.datastore.context;

import java.util.Properties;
import javax.sql.DataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class DatastoreConfigTest{
	
	@InjectMocks
	private TransactionManagerCreator cut;
	
	@Mock
	private LocalContainerEntityManagerFactoryBean entityManagerFactory;
	
	@Test
	public void createsTransactionManager(){
		PlatformTransactionManager tx = cut.create();
		assertThat(tx, instanceOf(JpaTransactionManager.class));
	}
}	
