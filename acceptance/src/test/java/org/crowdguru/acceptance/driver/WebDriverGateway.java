package org.crowdguru.acceptance.driver;

public class WebDriverGateway {

	public static WebDriverFactory getFirefoxFactory() {
		return new FirefoxDriverFactory();
	}
	
	public static WebDriverFactory getPhantomJSFactory() {
		return new PhantomJSDriverFactory();
	}
}
