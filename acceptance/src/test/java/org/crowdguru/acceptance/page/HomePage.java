package org.crowdguru.acceptance.page;

import org.openqa.selenium.WebDriver;

public class HomePage extends Page {

	private static final String CREATE_TASK_LINK_XPATH = "//a[contains(@href, '/tasks/new')]";
	
	public CreateTaskPage clickCreateTaskLink(){
		this.click(findElement(CREATE_TASK_LINK_XPATH));
		CreateTaskPage page = new CreateTaskPage(driver, baseUrl);
		return page;
	}
	
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


