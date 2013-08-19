package org.crowdguru.datastore.helpers.impl;

import org.crowdguru.datastore.constants.CauseConstraints;
import org.crowdguru.datastore.domain.Cause;
import org.crowdguru.datastore.helpers.CauseHelper;

public class CauseHelperImpl implements CauseHelper, CauseConstraints{

	@Override
	public Cause cause1() {
		Cause cause = new Cause();
		cause.setName(CAUSE_1_NAME);
		return cause;
	}
}
