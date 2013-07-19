package org.crowdguru.datastore.context;

import javax.sql.DataSource;

import org.cloudfoundry.runtime.env.CloudEnvironment;
import org.cloudfoundry.runtime.env.RdbmsServiceInfo;
import org.cloudfoundry.runtime.service.relational.RdbmsServiceCreator;
import org.junit.Before;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.env.EnvironmentCapable;

/**
 * Cloudfoundry dataSource configuration
 * 
 * @see <a href="http://docs.cloudfoundry.com/docs/using/services/spring-service-bindings.html#services">http://docs.cloudfoundry.com</a>
 * 
 * @author kabexnuf
 */
@Configuration
@Profile({ "cloud" })
public class CfDataSourceConfig{

	/**
	 * Cloudfoundry MySQL service name
	 */
	@Value("${cf.mysql.servicename}")
	private String serviceName;
	
	@Bean
	public DataSource dataSource() {
		CloudEnvironment env = new CloudEnvironment();
		RdbmsServiceInfo info = env.getServiceInfo(serviceName, RdbmsServiceInfo.class);
		return new RdbmsServiceCreator().createService(info);
	}
}
