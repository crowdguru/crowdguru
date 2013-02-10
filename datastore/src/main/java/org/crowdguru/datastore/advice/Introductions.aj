package org.crowdguru.datastore.advice;

import org.crowdguru.logging.IntroducedLogger;
import org.springframework.context.annotation.Configuration;

public aspect Introductions {
	declare @type: org.crowdguru.datastore.smoketest.App: @IntroducedLogger;
	declare @type: @Configuration * : @IntroducedLogger;
}
