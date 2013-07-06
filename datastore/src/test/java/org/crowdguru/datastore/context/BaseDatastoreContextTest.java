package org.crowdguru.datastore.context;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import java.util.Map;
import java.util.Properties;
import javax.sql.DataSource;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.PlatformTransactionManager;

@ContextConfiguration({"/datastoreContext.xml"})
public class BaseDatastoreContextTest {

	@Autowired
	protected CrowdGuruDatastoreContext contex;
	
	@Autowired
	protected LocalContainerEntityManagerFactoryBean factory;
	
	@Autowired
	protected DataSource dataSource;
	
	@Autowired
	protected PlatformTransactionManager tx;
	
	protected Properties sourceProps;
	
	protected Properties hibernateProps;

	@Test
	public void createsDataSource() {
		DriverManagerDataSource source = (DriverManagerDataSource) dataSource;
		assertThat(source, is(notNullValue()));
		assertThat(source, is(instanceOf(DriverManagerDataSource.class)));
		assertThat(source.getPassword(), 
				is(equalTo(sourceProps.getProperty("datastore.password"))));
		
		assertThat(source.getUsername(), 
				is(equalTo(sourceProps.getProperty("datastore.user"))));
		
		assertThat(source.getUrl(), 
				is(equalTo(sourceProps.getProperty("datastore.url"))));
	}

	@Test
	public void createsVendorAdapter() {
		HibernateJpaVendorAdapter vendor = 
				(HibernateJpaVendorAdapter)contex.vendorAdapter();
		
		assertThat(vendor, is(notNullValue()));
		
		assertThat(vendor, is(instanceOf(HibernateJpaVendorAdapter.class)));
	}

	@Test
	public void createsEntitiyManagerFactory() {
		assertThat(factory, is(notNullValue()));
		Map<String, Object> jpaProps = factory.getJpaPropertyMap();
		
		assertThat(factory.getPersistenceUnitName(), is(equalTo("crowdguru")));
		
		assertThat(jpaProps.size(), is(equalTo(hibernateProps.size())));
	
		for(Object value : hibernateProps.values()){
			assertThat(jpaProps, hasValue(value));
		}
	}

	@Test
	public void createsTransactionManager() {
		assertThat(tx, is(notNullValue()));
		
		assertThat(tx, is(instanceOf(JpaTransactionManager.class)));
	}

}