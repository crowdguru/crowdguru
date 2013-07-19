package org.crowdguru.datastore.context;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Non-cloud DataSource configuration
 * 
 * <p>
 * There are two profiles which enable this configuration class
 * <ul>
 * <li>
 * "dev" is used for H2 in-memory database. Configuration properties located in
 * {@link resources/META-INF/dev}
 * </li>
 * <li>
 * "prod" is used for MySQL database. Configuration properties located in
 * {@link resources/META-INF/prod}
 * </li>
 * </ul>
 * </p>
 * <p>
 * Database specific propertes are linked via 
 * {@link resources/datastoreContext.xml} 
 * </p>
 * 
 * @see CfDataSourceConfig
 * @see DatastoreConfig
 */
@Configuration
@Profile({ "dev", "prod" })
public class LocalDataSourceConfig {

	@Bean
	public DataSource dataSource() {
		log().info(
				"driver:" + datastoreDriver + " user:" + datastoreUser + " password:" + datastorePassword + " url: "
						+ datastoreUrl);

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(datastoreDriver);
		dataSource.setUrl(datastoreUrl);
		dataSource.setUsername(datastoreUser);
		dataSource.setPassword(datastorePassword);
		return dataSource;
	}

	/**
	 * JDBC driver class
	 */
	@Value("${datastore.driver}")
	private String datastoreDriver;

	/**
	 * JDBC Url
	 */
	@Value("${datastore.url}")
	private String datastoreUrl;

	/**
	 * JDBC username
	 */
	@Value("${datastore.user}")
	private String datastoreUser;

	/**
	 * JDBC password
	 */
	@Value("${datastore.password}")
	private String datastorePassword;
}
