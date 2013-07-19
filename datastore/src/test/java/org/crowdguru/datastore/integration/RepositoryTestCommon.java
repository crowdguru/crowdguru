package org.crowdguru.datastore.integration;

import org.crowdguru.datastore.helpers.DatabaseTesterHelper;
import org.crowdguru.datastore.helpers.FileOperationsHelper;
import org.dbunit.dataset.IDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.AfterTransaction;

@ContextConfiguration({ "/datastoreContext.xml", "/datastoreTestContext.xml" })
public class RepositoryTestCommon {

	@Autowired
	protected FileOperationsHelper fileHelper;

	@Autowired
	protected DatabaseTesterHelper databaseTester;

	protected IDataSet initialData;

	protected Runnable check;
	
	/**
	 * Different test methods can create different checks after transactional
	 * operations.
	 */
	@AfterTransaction
	public void performCheck() {
		if (check != null) {
			check.run();
		}
	}
}
