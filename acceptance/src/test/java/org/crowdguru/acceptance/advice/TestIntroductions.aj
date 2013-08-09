package org.crowdguru.acceptance.advice;

import org.crowdguru.logging.IntroducedLogger;

public aspect TestIntroductions {
	declare @type: org.crowdguru.acceptance.usecase.*: 		@IntroducedLogger;
	declare @type: org.crowdguru.acceptance.predicate.*: 		@IntroducedLogger;
	declare @type: org.crowdguru.acceptance.page.*: 		@IntroducedLogger;
}
