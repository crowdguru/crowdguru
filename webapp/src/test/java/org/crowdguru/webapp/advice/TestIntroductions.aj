package org.crowdguru.webapp.advice;

import org.crowdguru.logging.IntroducedLogger;
import org.crowdguru.webapp.views.BaseTest;

public aspect TestIntroductions {
	declare @type: BaseTest+ : @IntroducedLogger;
}
