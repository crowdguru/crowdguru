package org.crowdguru.webapp.views;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class IndexPageTest extends BaseTest {
	@Test
	public void testTitle() throws Exception {
		WebDriver driver = this.getDriver();
        driver.get(this.getBaseURL());
        log().warn("Page title: '" + driver.getTitle() + "'");
        log().warn("Url: '" + driver.getCurrentUrl() + "'");
        assertTrue(driver.getTitle().contains("CrowdGuru"));
	}
}
