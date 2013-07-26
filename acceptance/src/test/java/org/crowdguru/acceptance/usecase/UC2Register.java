package org.crowdguru.acceptance.usecase;

import org.crowdguru.acceptance.driver.WebDriverFactory;
import org.crowdguru.acceptance.driver.WebDriverGateway;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UC2Register{

	WebDriverFactory driverFactory = WebDriverGateway.getPhantomJSFactory();
	WebDriver driver;
	
	@Before
	public void setUp() throws Exception {
		driver = driverFactory.create();
	}
	
	@Test
	public void testTitle() throws Exception {
        driver.get("http://localhost:8080/crowdguru");
        log().info("Index page loaded");
        driver.findElement(By.xpath("//a[contains(text(),'Sign in / register')]"))
        	.click();
        
        driver.findElement(By.xpath("//a[contains(text(),'Register')]"))
        	.click();
        
        driver.findElement(By.xpath("//input[@id='inputEmail']"))
        	.clear();
        
        driver.findElement(By.xpath("//input[@id='inputEmail']"))
        	.sendKeys("user1@crowdguru.org");
        
        driver.findElement(By.xpath("//input[@id='inputPassword']"))
        	.clear();
        
        driver.findElement(By.xpath("//input[@id='inputPassword']"))
        	.sendKeys("password1");
        
        driver.findElement(By.xpath("//input[@value='Save details and continue registration later']"))
    		.click();
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
}
