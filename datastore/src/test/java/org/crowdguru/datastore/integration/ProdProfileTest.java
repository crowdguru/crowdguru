package org.crowdguru.datastore.integration;

import java.sql.SQLException;
import java.util.Map;
import javax.sql.DataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles({"prod"})
@ContextConfiguration({"/datastoreContext.xml"})
public class ProdProfileTest{
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private PlatformTransactionManager tx;
	
	@Autowired
	private LocalContainerEntityManagerFactoryBean entityManagerFactory;
	
	@Test
	public void initializesDataSource() throws SQLException {
		assertThat(dataSource.getConnection(), notNullValue());
	}
	
	@Test
	public void initializesTransactionManager() throws SQLException {
		assertThat(tx, notNullValue());
		assertThat(tx, instanceOf(JpaTransactionManager.class));
	}
	
	@Test
	public void initializesHibernate() throws SQLException {
		JpaDialect dialect = entityManagerFactory.getJpaDialect();
		assertThat(dialect, instanceOf(HibernateJpaDialect.class));
	}
	
	@Test
	public void initializesMySQLDatabase() throws SQLException {
		Map<String, Object> jpaProperties = entityManagerFactory.getJpaPropertyMap();
		assertThat(jpaProperties.values(), not(empty()));
		assertThat((String)jpaProperties.get("hibernate.dialect"), is(equalTo("org.hibernate.dialect.MySQLDialect")));
	}
	
	
}