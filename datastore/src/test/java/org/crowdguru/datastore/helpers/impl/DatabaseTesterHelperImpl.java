package org.crowdguru.datastore.helpers.impl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.crowdguru.datastore.helpers.DatabaseTesterHelper;
import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.annotation.Transactional;

public class DatabaseTesterHelperImpl implements DatabaseTesterHelper {

	DataSource dataSource;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}

	@Override
	public void clean() throws SQLException, Exception {
		IDatabaseConnection dbunitConnection = getJdbcConnection();
		IDataSet dataset = dbunitConnection.createDataSet();
		DatabaseOperation.DELETE_ALL.execute(dbunitConnection, dataset);
	}

	@Override
	public void cleanInsert(IDataSet dataset) throws DatabaseUnitException, SQLException, Exception {
		IDatabaseConnection dbunitConnection = getJdbcConnection();
		DatabaseOperation.CLEAN_INSERT.execute(dbunitConnection, dataset);
	}

	@Override
	public IDataSet getDataSet() throws SQLException, Exception {
		IDatabaseConnection dbunitConnection = getJdbcConnection();
		IDataSet dataset = dbunitConnection.createDataSet();
		releaseManagedDatabaseConnection(dbunitConnection.getConnection());
		return dataset;
	}

	private IDatabaseConnection getJdbcConnection() throws CannotGetJdbcConnectionException, DatabaseUnitException {
		return new DatabaseConnection(DataSourceUtils.getConnection(dataSource));
	}

	private void releaseManagedDatabaseConnection(Connection connection) throws CannotGetJdbcConnectionException,
			DatabaseUnitException {
		 DataSourceUtils.releaseConnection(connection, dataSource);
	}
	
	@Override
	public boolean disableForeignKeyIntegrityCheck() throws SQLException, CannotGetJdbcConnectionException, DatabaseUnitException{
		IDatabaseConnection dbunitConnection = getJdbcConnection();
		return executeSqlStatement(dbunitConnection, "SET FOREIGN_KEY_CHECKS = 0;");
	}
	
	@Override
	public boolean enableForeignKeyIntegrityCheck() throws CannotGetJdbcConnectionException, DatabaseUnitException, SQLException{
		IDatabaseConnection dbunitConnection = getJdbcConnection();
		return executeSqlStatement(dbunitConnection, "SET FOREIGN_KEY_CHECKS = 1;");
	}
	
	private boolean executeSqlStatement(IDatabaseConnection dbunitConnection, String statement) throws SQLException{
		return dbunitConnection.getConnection().prepareStatement(statement).execute();
	}
}
