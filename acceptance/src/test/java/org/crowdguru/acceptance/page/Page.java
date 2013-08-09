package org.crowdguru.acceptance.page;

import static org.junit.Assert.assertTrue;

import org.crowdguru.acceptance.predicate.PageLoadedPredicate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {

	protected WebDriver driver;
	
	protected String baseUrl;
	
	protected String path;
	
	protected String url;
	
	public Page(WebDriver driver, String baseUrl, String path) {
		super();
		this.driver = driver;
		this.baseUrl = baseUrl;
		this.path = path;
		this.url = baseUrl + path;
		log().debug("url=" + this.url);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(new PageLoadedPredicate(url));
	}

	protected void click(WebElement element) {
		element.click();
	}
	
	protected WebElement findElement(String xpath){
		return driver.findElement(By.xpath(xpath));
	}
	
	protected void clear(WebElement element){
		element.clear();
	}
	
	protected void sendKeys(WebElement element, String input){
		element.sendKeys(input);
	}
	
	protected void clearAndSendKeys(WebElement element, String input){
		this.clear(element);
		this.sendKeys(element, input);
	}
	
	public void assertTextPresentWithRE(String re){
		 assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches(re));
	}
	
	public void assertTextPresentAsWhole(String text){
		String re = "^[\\s\\S]*" + text + "[\\s\\S]*$";
		assertTextPresentWithRE(re);
	}
}