package org.crowdguru.acceptance.page;

import org.openqa.selenium.WebDriver;

public class ProfilePage extends Page {

	private static final String CREATE_TASK_LINK_XPATH = "//a[contains(@href, '/tasks/new')]";
	
	public CreateTaskPage clickCreateTaskLink(){
		this.click(findElement(CREATE_TASK_LINK_XPATH));
		CreateTaskPage page = new CreateTaskPage(driver, baseUrl);
		return page;
	}
	
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


