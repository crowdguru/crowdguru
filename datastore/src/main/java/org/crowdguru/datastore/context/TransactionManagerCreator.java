package org.crowdguru.datastore.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@Profile({ "dev", "prod", "cloud" })
public class TransactionManagerCreator {

	private LocalContainerEntityManagerFactoryBean entityManagerFactory;
	
	@Autowired
	public void setEntityManagerFactory(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	@Bean(name="transactionManager")
	public PlatformTransactionManager create(){
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
		return transactionManager;
	}
}
