package org.crowdguru.acceptance.page;

import org.openqa.selenium.WebDriver;

public class HomePage extends PageWithNavBar {

	public HomePage(WebDriver driver, String baseUrl){
		super(driver, baseUrl, "/home");
	}
	
	public void assertProfileField(String forename) {
		this.assertTextPresentWithRE(constructRE(forename));
	}
	
	private String constructRE(String value){
		return "^[\\s\\S]*" + value + "[\\s\\S]*$";
	}
}


