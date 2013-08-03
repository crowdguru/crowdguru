package org.crowdguru.datastore.context;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("dev")
@PropertySource(value = "classpath:/META-INF/h2.properties")
public class H2DataSourceCreator extends PropertyFileConfigurableDataSource{

}
