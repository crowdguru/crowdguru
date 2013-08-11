package org.crowdguru.webapp.advice;

import org.crowdguru.logging.IntroducedLogger;

public aspect TestIntroductions {
	declare @type: org.crowdguru.webapp.integration..* : @IntroducedLogger;
}
