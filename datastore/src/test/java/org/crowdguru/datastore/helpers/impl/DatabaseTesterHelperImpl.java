package org.crowdguru.datastore.helpers.impl;

import java.sql.SQLException;

import org.crowdguru.datastore.helpers.DatabaseTesterHelper;
import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.beans.factory.annotation.Autowired;

public class DatabaseTesterHelperImpl implements DatabaseTesterHelper {

	@Autowired
	private DataSourceDatabaseTester databaseTester;

	@Override
	public void clean() throws SQLException, Exception {
		IDataSet dataset = databaseTester.getConnection().createDataSet();
		DatabaseOperation.DELETE_ALL.execute(databaseTester.getConnection(), dataset);
	}

	@Override
	public void cleanInsert(IDataSet dataset) throws DatabaseUnitException, SQLException, Exception {
		DatabaseOperation.CLEAN_INSERT.execute(databaseTester.getConnection(), dataset);
	}

	@Override
	public IDatabaseConnection getConnection() throws Exception {
		return databaseTester.getConnection();
	}

	@Override
	public IDataSet getDataSet() throws SQLException, Exception {
		return databaseTester.getConnection().createDataSet();
	}

}
