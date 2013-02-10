package org.crowdguru.datastore.advice;

import org.crowdguru.logging.IntroducedLogger;

public aspect Introductions {
	declare @type: org.crowdguru.datastore.smoketest.App: @IntroducedLogger;
}
