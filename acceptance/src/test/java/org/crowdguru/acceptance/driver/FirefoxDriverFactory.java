package org.crowdguru.acceptance.driver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverFactory implements WebDriverFactory {

	@Override
	public WebDriver create() {
		FirefoxDriver driver = new FirefoxDriver(); 
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}
}
