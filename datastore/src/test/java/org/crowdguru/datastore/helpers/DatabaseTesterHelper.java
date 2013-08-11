package org.crowdguru.datastore.helpers;

import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;

/**
 * Provides database operations. Especially bringing it to a known state before
 * running test methods.
 * 
 * @author kabexnuf
 * 
 */
public interface DatabaseTesterHelper {

	/**
	 * Cleans all tables
	 * 
	 * @throws SQLException
	 * 
	 * @throws Exception
	 */
	public void clean() throws SQLException, Exception;

	/**
	 * Cleans all tables and initializes with a given dataset. See
	 * {@link #getDataSet() getDataSet}
	 * 
	 * @param dataset
	 * 
	 * @throws DatabaseUnitException
	 * 
	 * @throws SQLException
	 * 
	 * @throws Exception
	 */
	public void cleanInsert(IDataSet dataset) throws DatabaseUnitException, SQLException, Exception;

	/**
	 * Provides current database content with all tables.
	 * 
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public IDataSet getDataSet() throws SQLException, Exception;
}
