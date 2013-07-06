package org.crowdguru.datastore.helpers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import org.junit.Before;
import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;

/**
 * Provides import/export services of database in different file formats.
 * 
 * @author kabexnuf
 */
public interface FileOperationsHelper {

	/**
	 * Exports database content as a flat xml file
	 * 
	 * @param path File path to be created
	 * 
	 * @throws DataSetException
	 * 
	 * @throws FileNotFoundException
	 * 
	 * @throws IOException
	 * 
	 * @throws SQLException
	 * 
	 * @throws Exception
	 */
	public void exportToFlatXmlFile(IDataSet dataset, String path) throws DataSetException, FileNotFoundException, IOException,
			SQLException, Exception;

	/**
	 * Exports database content as a xls file.
	 * TODO Not implemented yet
	 * 
	 * @param path File path to create
	 */
	public void exportToExcelFile(String path);

	/**
	 * Clears all tables and initializes with the file content specified by path
	 * TODO Not implemented yet
	 * 
	 * @param path File path to the source
	 */
	public void initializeWithExcelFile(String path);
	
	/**
	 * Load dataset from a flat xml file
	 * 
	 * @param path File name or path after dataset path
	 * 
	 * @return Dataset
	 * 
	 * @throws MalformedURLException
	 * @throws DataSetException
	 */
	public IDataSet loadFromFlatXmlFile(String path) throws MalformedURLException, DataSetException;
}
