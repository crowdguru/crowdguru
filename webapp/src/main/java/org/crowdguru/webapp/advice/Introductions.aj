package org.crowdguru.webapp.advice;

import org.crowdguru.logging.IntroducedLogger;
import org.springframework.stereotype.Controller;

public aspect Introductions {
	declare @type: @Controller * : @IntroducedLogger;
}
