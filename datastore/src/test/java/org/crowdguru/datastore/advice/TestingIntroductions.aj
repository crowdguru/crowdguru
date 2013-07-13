package org.crowdguru.datastore.advice;

import org.crowdguru.logging.IntroducedLogger;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

public aspect TestingIntroductions {
	declare @type: org.crowdguru.datastore.helpers.impl.*: 		@Component;
	declare @type: org.crowdguru.datastore.validators.impl.*: 	@Component;
	declare @type: org.crowdguru.datastore.helpers.impl.*: 		@IntroducedLogger;
	declare @type: org.crowdguru.datastore.validators.impl.*: 	@IntroducedLogger;
	declare @type: org.crowdguru.datastore.repository.Base*:    @IntroducedLogger;
	declare @type: org.crowdguru.datastore.domain.*:    	    @IntroducedLogger;
	declare @type: org.crowdguru.datastore.integration.*:  	    @IntroducedLogger;
	declare @type: org.crowdguru.datastore.repository.BaseRepositoryTest : @ActiveProfiles({"dev", "unittest"});
}
