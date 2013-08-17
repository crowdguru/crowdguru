package org.crowdguru.acceptance.page;

import org.openqa.selenium.WebDriver;

public class IndexPage extends Page {
	
	private static final String LOGON_BUTTON_XPATH =  "//button[@id='buttonLogon']";
	
	private static final String EMAIL_INPUT_XPATH = "//input[@name='email']";
	
	private static final String PASSWORD_INPUT_XPATH = "//input[@name='password']";
	
	private static final String GURU_BUTTON_XPATH = "//button[@id='guru']";
	
	private static final String KEYCONTACT_BUTTON_XPATH = "//button[@id='keyContact']";
	
	public IndexPage(WebDriver driver, String baseUrl){
		super(driver, baseUrl, "/");
	}
	
	public void typeEmail(String email) {
		this.clearAndSendKeys(findElement(EMAIL_INPUT_XPATH), email);
	}

	public void typePassword(String password) {
		this.clearAndSendKeys(findElement(PASSWORD_INPUT_XPATH), password);		
	}
	
	public ProfilePage login(String email, String password){
		typeEmail(email);
		typePassword(password);
		return clickLogOnButton();
	}
	
	public ProfilePage clickLogOnButton() {
		this.click(findElement(LOGON_BUTTON_XPATH));
		ProfilePage page = new ProfilePage(driver, baseUrl);
		return page;
	}
	
	public RegisterPage clickBecomeGuruButton() {
		this.click(findElement(GURU_BUTTON_XPATH));
		RegisterPage page = new RegisterPage(driver, baseUrl);
		return page;
	}
	
	public RegisterPage clickBecomeKeyContactButton() {
		this.click(findElement(KEYCONTACT_BUTTON_XPATH));
		RegisterPage page = new RegisterPage(driver, baseUrl);
		return page;
	}
	
}
