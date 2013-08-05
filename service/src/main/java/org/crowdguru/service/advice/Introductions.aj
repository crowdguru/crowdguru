package org.crowdguru.service.advice;

import org.crowdguru.logging.IntroducedLogger;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Profile;

public aspect Introductions {
	declare @type: org.crowdguru.service.gateway.* : @Component;
	declare @type: org.crowdguru.service.domain.impl.* : @Service;
	declare @type: org.crowdguru.service.domain.stub.*: @Service;
	declare @type: org.crowdguru.service.domain.stub.*: @Profile({"nojpa"});
	declare @type: @Component org.crowdguru.service..* : @IntroducedLogger;
	declare @type: @Service org.crowdguru.service..* : @IntroducedLogger;
}
