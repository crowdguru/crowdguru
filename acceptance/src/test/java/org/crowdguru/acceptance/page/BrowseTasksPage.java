package org.crowdguru.acceptance.page;

import org.openqa.selenium.WebDriver;

public class BrowseTasksPage extends Page{
	
	public BrowseTasksPage(WebDriver driver, String baseUrl){
		super(driver, baseUrl, "/tasks");
	}
	
	public void assertTaskField(String field) {
		this.assertTextPresentWithRE(constructRE(field));
	}
	
	private String constructRE(String value){
		return "^[\\s\\S]*" + value + "[\\s\\S]*$";
	}
}
