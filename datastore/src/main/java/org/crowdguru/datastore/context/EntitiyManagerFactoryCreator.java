package org.crowdguru.datastore.context;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
@Profile({ "dev", "prod", "cloud" })
@PropertySource(value="classpath:META-INF/prod/hibernate.properties")
public class EntitiyManagerFactoryCreator {
	
	private Properties hibernateProperties;
	
	private DataSource dataSource;
	
	private JpaVendorAdapter jpaVendorAdapter;

	public EntitiyManagerFactoryCreator() {
		log().warn("state=created");
	}
	
	@Autowired
	public void setHibernateProperties(Properties hibernateProperties) {
		this.hibernateProperties = hibernateProperties;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Autowired
	public void setJpaVendorAdapter(JpaVendorAdapter jpaVendorAdapter) {
		this.jpaVendorAdapter = jpaVendorAdapter;
	}

	@Bean(name= "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean create(){
		log().warn("activity=create");
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(dataSource);
		factory.setJpaVendorAdapter(jpaVendorAdapter);
		factory.setPersistenceUnitName("crowdguru");
		factory.setJpaProperties(hibernateProperties);
		return factory;
	}
}