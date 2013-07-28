package org.crowdguru.datastore.context;

import org.cloudfoundry.runtime.env.CloudEnvironment;
import org.cloudfoundry.runtime.env.RdbmsServiceInfo;
import org.springframework.stereotype.Component;

@Component
public class CloudFoundryEnvironmentGateway {

	private CloudEnvironment environment;
	
	public CloudFoundryEnvironmentGateway() {
		this.environment = new CloudEnvironment();
	}
	
	public RdbmsServiceInfo getRdbmsServiceInfo(String serviceName) {
		return environment.getServiceInfo(serviceName, RdbmsServiceInfo.class);
	}
}
