package org.crowdguru.datastore.helpers.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;

import org.crowdguru.datastore.helpers.DatabaseTesterHelper;
import org.crowdguru.datastore.helpers.FileOperationsHelper;
import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.springframework.beans.factory.annotation.Autowired;

public class FileOperationsHelperImpl implements FileOperationsHelper {
	
	@Autowired
	private String dataPath;
	
	@Override
	public void exportToFlatXmlFile(IDataSet dataset, String path) throws DataSetException, FileNotFoundException, IOException {
		FlatXmlDataSet.write(dataset, new FileOutputStream(dataPath + path));
	}

	@Override
	public void exportToExcelFile(String path) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initializeWithExcelFile(String path) {
		// TODO Auto-generated method stub

	}
	
	public IDataSet loadFromFlatXmlFile(String path) throws MalformedURLException, DataSetException{
		FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
		return builder.build(new File(dataPath + path));
	}

}
