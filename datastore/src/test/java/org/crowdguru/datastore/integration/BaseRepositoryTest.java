package org.crowdguru.datastore.integration;

import org.crowdguru.datastore.helpers.DatabaseTesterHelper;
import org.crowdguru.datastore.helpers.FileOperationsHelper;
import org.dbunit.dataset.IDataSet;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/datastoreContext.xml", "/datastoreTestContext.xml" })
public abstract class BaseRepositoryTest {

	@Autowired
	protected FileOperationsHelper fileHelper;

	@Autowired
	protected DatabaseTesterHelper databaseTester;

	protected IDataSet initialData;

	protected Runnable check;
}
