package org.crowdguru.datastore.context;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public abstract class PropertyFileConfigurableDataSource extends PropertyConfigurable 
	implements DataSourceCreator {

	private final static String DEFAULT_DRIVER_KEY = "datastore.driver";

	private final static String DEFAULT_URL_KEY = "datastore.url";

	private final static String DEFAULT_USERNAME_KEY = "datastore.user";

	private final static String DEFAULT_PASSWORD_KEY = "datastore.password";

	protected String getDriver() {
		return getProperty(DEFAULT_DRIVER_KEY);
	}

	protected String getUrl() {
		return getProperty(DEFAULT_URL_KEY);
	}

	protected String getUserName() {
		return getProperty(DEFAULT_USERNAME_KEY);
	}

	protected String getPassword() {
		return getProperty(DEFAULT_PASSWORD_KEY);
	}

	@Bean(name = "dataSource")
	public DataSource create() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(getDriver());
		dataSource.setUrl(getUrl());
		dataSource.setUsername(getUserName());
		dataSource.setPassword(getPassword());
		return dataSource;
	}
}
