package org.crowdguru.datastore.context;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("prod")
@PropertySource(value = "classpath:/META-INF/mysql.properties")
public class MysqlDataSourceCreator extends PropertyFileConfigurableDataSource{

}
