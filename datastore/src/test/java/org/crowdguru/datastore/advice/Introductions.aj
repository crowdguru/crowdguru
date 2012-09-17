package org.crowdguru.datastore.advice;

import org.crowdguru.logging.IntroducedLogger;
import org.springframework.stereotype.Component;

public aspect Introductions {
	declare @type: org.crowdguru.datastore.helpers.impl.*: 		@Component;
	declare @type: org.crowdguru.datastore.validators.impl.*: 	@Component;
	declare @type: org.crowdguru.datastore.helpers.impl.*: 		@IntroducedLogger;
	declare @type: org.crowdguru.datastore.validators.impl.*: 	@IntroducedLogger;
	declare @type: org.crowdguru.datastore.tests.*: 			@IntroducedLogger;
}
