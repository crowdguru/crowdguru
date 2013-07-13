package org.crowdguru.datastore.context;
import java.util.Properties;
import javax.sql.DataSource;
import org.crowdguru.datastore.exceptions.CrowdGuruDatastoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@Profile({ "dev", "prod" })
public class CrowdGuruDatastoreContext {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(dataSource());
		factory.setJpaVendorAdapter(vendorAdapter());
		factory.setPersistenceUnitName("crowdguru");
		factory.setJpaProperties(hibernateProperties);
		return factory;
	}

	@Bean
	public JpaVendorAdapter vendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		return adapter;
	}

	@Bean
	public PlatformTransactionManager transactionManager(){
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}

	@Bean
	public DataSource dataSource() {
		log().info("driver:" + datastoreDriver + " user:" + datastoreUser + 
				" password:" + datastorePassword + " url: " + datastoreUrl);
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(datastoreDriver);
		dataSource.setUrl(datastoreUrl);
		dataSource.setUsername(datastoreUser);
		dataSource.setPassword(datastorePassword);
		return dataSource;
	}

	@Value("${datastore.driver}")
	private String datastoreDriver;

	@Value("${datastore.url}")
	private String datastoreUrl;

	@Value("${datastore.user}")
	private String datastoreUser;

	@Value("${datastore.password}")
	private String datastorePassword;

	@Autowired
	private Properties hibernateProperties;
}
