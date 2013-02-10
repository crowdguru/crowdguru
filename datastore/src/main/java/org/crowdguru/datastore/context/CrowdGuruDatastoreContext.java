package org.crowdguru.datastore.context;

import org.crowdguru.datastore.exceptions.CrowdGuruDatastoreException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@ImportResource("classpath:datastoreContext.xml")
public class CrowdGuruDatastoreContext {

	@Bean(name = "crowdGuruEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean crowdGuruEntityManagerFactory() throws CrowdGuruDatastoreException {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setPersistenceUnitName("crowdguru-" + env);
		return factory;
	}

	@Bean(name = "crowdGuruTransactionManager")
	public PlatformTransactionManager crowdGuruTransactionManager() throws CrowdGuruDatastoreException {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(crowdGuruEntityManagerFactory().getObject());
		return transactionManager;
	}

	@Value("${env}")
	private String env;
}
