package org.crowdguru.webapp.advice;

import org.crowdguru.logging.IntroducedLogger;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

public aspect Introductions {
	declare @type: @Controller org.crowdguru..* : @IntroducedLogger;
	declare @type: org.crowdguru.webapp.security.*: @IntroducedLogger;
	declare @type: @Service org.crowdguru..* : @IntroducedLogger;
}
