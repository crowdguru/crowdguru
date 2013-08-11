package org.crowdguru.acceptance.page;

import org.openqa.selenium.WebDriver;

public class LoginPage extends Page{

	private static final String REGISTER_BUTTON_XPATH = "//a[contains(text(),'Register')]";
	
	private static final String EMAIL_INPUT_XPATH = "//input[@name='email']";
	
	private static final String PASSWORD_INPUT_XPATH = "//input[@name='password']";
	
	private static final String LOGON_BUTTON_XPATH = "//input[@value='Log on']";
	
	public LoginPage(WebDriver driver, String baseUrl){
		super(driver, baseUrl, "/login");
	}
	
	public RegisterPage clickRegisterButton(){
		this.click(findElement(REGISTER_BUTTON_XPATH));
		RegisterPage page = new RegisterPage(driver, baseUrl);
		return page;
	}

	public void typeEmail(String email) {
		this.clearAndSendKeys(findElement(EMAIL_INPUT_XPATH), email);
	}

	public void typePassword(String password) {
		this.clearAndSendKeys(findElement(PASSWORD_INPUT_XPATH), password);		
	}

	public ProfilePage clickLogOnButton() {
		this.click(findElement(LOGON_BUTTON_XPATH));
		ProfilePage page = new ProfilePage(driver, baseUrl);
		return page;
	}
	
	public ProfilePage login(String email, String password){
		typeEmail(email);
		typePassword(password);
		return clickLogOnButton();
	}
}
