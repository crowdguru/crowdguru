package org.crowdguru.datastore.advice;

import org.crowdguru.logging.IntroducedLogger;
import org.springframework.context.annotation.Configuration;
import org.crowdguru.datastore.context.PropertyConfigurable;

public aspect Introductions {
	declare @type: @Configuration * : @IntroducedLogger;
	declare @type: PropertyConfigurable+ : @IntroducedLogger;
}
