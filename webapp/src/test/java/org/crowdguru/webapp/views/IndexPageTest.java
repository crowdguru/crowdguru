package org.crowdguru.webapp.views;

import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.WebDriver;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.logging.Logger;

public class IndexPageTest extends BaseTest{
	@Test
	public void testTitle() throws Exception {
		WebDriver driver = this.getDriver();
        driver.get(this.getBaseURL());
        Logger LOG = Logger.getLogger(PhantomJSDriverService.class.getName());
        LOG.warning("Page title: " + driver.getTitle());
        LOG.warning("Url: " + driver.getCurrentUrl());
        assertTrue(driver.getTitle().contains("CrowdGuru"));
        
        //Take a screenshot 
        //FileUtils.copyFile(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE),new File("screenshot.png")); 
	}
}
