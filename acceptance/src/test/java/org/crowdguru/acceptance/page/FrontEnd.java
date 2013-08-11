package org.crowdguru.acceptance.page;

import org.openqa.selenium.WebDriver;
import static org.junit.Assert.assertTrue;

public class FrontEnd {

	private static final String DEFAULT_BASE_URL = "http://localhost:8080/crowdguru";
	
	private String baseUrl;
	
	private WebDriver driver;
	
	public FrontEnd(){
		baseUrl = DEFAULT_BASE_URL;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	
	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public IndexPage goHome(){
		driver.get(baseUrl);
		IndexPage page = new IndexPage(driver, baseUrl);
		return page;
	}

	public void logOut() {
		driver.get(baseUrl + "/logout");
	}
}
