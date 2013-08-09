package org.crowdguru.acceptance.page;

import org.openqa.selenium.WebDriver;

public class ProfilePage extends Page {

	public ProfilePage(WebDriver driver, String baseUrl){
		super(driver, baseUrl, "/profile");
	}
	
	public void assertProfileField(String forename) {
		this.assertTextPresentWithRE(constructRE(forename));
	}
	
	private String constructRE(String value){
		return "^[\\s\\S]*" + value + "[\\s\\S]*$";
	}
}


