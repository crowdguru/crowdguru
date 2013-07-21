package org.crowdguru.acceptance.driver;

import java.io.File;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.os.CommandLine;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.phantomjs.PhantomJSDriverService.Builder;
import org.openqa.selenium.remote.DesiredCapabilities;

public class PhantomJSDriverFactory implements WebDriverFactory {

	public PhantomJSDriverFactory() {
		
	}
	@Override
	public WebDriver create() throws Exception {
		DesiredCapabilities caps = prepareCapabilities();
		Builder builder = prepareBuilder();
		WebDriver driver = new PhantomJSDriver(builder.build(), caps); 
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().setSize(new Dimension(1024, 768));
		return driver;
	}
	
	private DesiredCapabilities prepareCapabilities() {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setJavascriptEnabled(true);
		caps.setCapability("takesScreenshot", true);
		return caps;
	}
	
	private Builder prepareBuilder() throws Exception {
		Builder builder = new PhantomJSDriverService.Builder()
				.withLogFile(new File("target/log/phantomjs.log"))
				.usingCommandLineArguments(new String[] { "--webdriver-loglevel=ERROR"})
				.usingPhantomJSExecutable(new File(findPhantomJSExecutablePath()));
		
		return builder;
	}

	@SuppressWarnings("deprecation")
	private String findPhantomJSExecutablePath() {
		return CommandLine.find("phantomjs");
	}
}
