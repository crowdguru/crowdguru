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
import org.springframework.context.annotation.PropertySource;
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
@PropertySource(value="classpath:/META-INF/cloud/cloudfoundry.properties")
public class CloudFoundryfDataSourceCreator extends PropertyConfigurable implements DataSourceCreator{

	private static final String MYSQL_SERVICE_NAME_KEY = "cf.mysql.servicename";
	
	private CloudFoundryEnvironmentGateway cloudFoundryEnvironment;
	
	public CloudFoundryfDataSourceCreator() {
		log().warn("state=created");
	}

	@Autowired
	public void setCloudFoundryEnvironment(CloudFoundryEnvironmentGateway cloudFoundryEnvironment) {
		this.cloudFoundryEnvironment = cloudFoundryEnvironment;
	}


	@Override
	@Bean(name="dataSource")
	public DataSource create() {
		log().warn("activity=create");
		String mysqlServiceName = getProperty(MYSQL_SERVICE_NAME_KEY);
		RdbmsServiceInfo info = cloudFoundryEnvironment.getRdbmsServiceInfo(mysqlServiceName);
		return new RdbmsServiceCreator().createService(info);
	}
	
	
}
