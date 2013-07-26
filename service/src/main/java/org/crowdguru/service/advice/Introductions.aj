package org.crowdguru.service.advice;

import org.crowdguru.logging.IntroducedLogger;
import org.springframework.stereotype.Service;

public aspect Introductions {
	declare @type: @Service org.crowdguru.service..* : @IntroducedLogger;
}
