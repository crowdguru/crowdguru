package org.crowdguru.datastore.context;

import javax.sql.DataSource;

public interface DataSourceCreator {
	
	public DataSource create();
}
