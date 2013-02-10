package org.crowdguru.datastore.context;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.crowdguru.datastore.exceptions.CrowdGuruDatastoreException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@ImportResource("classpath:datastoreContext.xml")
public class CrowdGuruDatastoreContext {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws CrowdGuruDatastoreException {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(dataSource());
		factory.setPersistenceUnitName("crowdguru");
		factory.setJpaVendorAdapter(vendorAdapter());
		return factory;
	}

	@Bean
	public JpaVendorAdapter vendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setShowSql(StringUtils.isNotBlank(System.getProperty("jpa.showsql"))
				&& System.getProperty("jpa.showsql").equals("true"));
		adapter.setGenerateDdl(true);
		adapter.setDatabase(Database.MYSQL);
		return adapter;
	}

	@Bean
	public PlatformTransactionManager transactionManager() throws CrowdGuruDatastoreException  {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource  dataSource= new DriverManagerDataSource();
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
}
