package org.crowdguru.acceptance.page;

import org.openqa.selenium.WebDriver;

public class IndexPage extends Page {
	
	private static final String LOGON_REGISTER_BUTTON_XPATH =  "//a[contains(text(),'Log on / register')]";
	
	public IndexPage(WebDriver driver, String baseUrl){
		super(driver, baseUrl, "/");
	}
	
	public LoginPage clickLogOnButton(){
		this.click(findElement(LOGON_REGISTER_BUTTON_XPATH));
		LoginPage page = new LoginPage(driver, baseUrl);
		return page;
	}
	
}
