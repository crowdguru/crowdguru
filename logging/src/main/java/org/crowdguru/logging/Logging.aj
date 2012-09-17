package org.crowdguru.logging;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.SuppressAjWarnings;

public aspect Logging {
	
	declare parents: @IntroducedLogger * implements Logs;

	public Log Logs.log() {
		return LogFactory.getLog(this.getClass());
	}
	
	pointcut debugOp(Log log) : call(void Log.debug(..)) && target(log);

	@SuppressAjWarnings("adviceDidNotMatch")  // it might
	Object around(Log log): debugOp(log) {
		Object returnValue = null;
		if (log.isDebugEnabled()) {
			returnValue = proceed(log);
		}
		return returnValue;
	}
	
}
