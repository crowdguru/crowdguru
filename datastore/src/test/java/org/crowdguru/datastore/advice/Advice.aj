package org.crowdguru.datastore.advice;

import org.crowdguru.datastore.tests.BaseTest;
import org.junit.Test;

public aspect Advice {
	
	pointcut testExecutionOperation(BaseTest testClass):
        execution(@Test public void org.crowdguru.datastore.tests.BaseTest+.*())
        && this(testClass);

	before(BaseTest testClass): testExecutionOperation(testClass) {
		testClass.clearDown();
	}
}
