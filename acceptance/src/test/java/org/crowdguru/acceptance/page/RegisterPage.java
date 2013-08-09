package org.crowdguru.acceptance.page;

import org.crowdguru.acceptance.predicate.ElementDisplayedPredicate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;

public class RegisterPage extends Page {

	private static final String FORENAME_INPUT_XPATH = "//input[@name='forename']";
	
	private static final String SURNAME_INPUT_XPATH = "//input[@name='surname']";
	
	private static final String EMAIL_INPUT_XPATH = "//input[@name='email']";
	
	private static final String PASSWORD_INPUT_XPATH = "//input[@name='password']";
	
	private static final String GURU_CHECKBOX_XPATH = "//input[@name='guru']";
	
	private static final String KEYCONTACT_CHECKBOX_XPATH = "//input[@name='keyContact']";
	
	private static final String SUBMIT_BUTTON_XPATH = "//input[@value='Submit']";
	
	private static final String SUBMISSION_SUCCESS_NOTIFICATION_RE = "^[\\s\\S]*Submitted successfuly! Check your inbox to activate your account\\.[\\s\\S]*$";
	
	private static final String SUBMISSION_NOTIFICATION_CLOSE_BUTTON_XPATH = "//div[@id='divSubmitSuccessNotification']/button";
	
	private static final String SUBMISSION_NOTIFICATION_DIV_XPATH = "//div[@id='divSubmitSuccessNotification']";
	
	public RegisterPage(WebDriver driver, String baseUrl){
		super(driver, baseUrl, "/register");
	}
	
	public void typeForename(String forename){
		this.clearAndSendKeys(findElement(FORENAME_INPUT_XPATH), forename);
	}

	public void typeSurname(String surname) {
		this.clearAndSendKeys(findElement(SURNAME_INPUT_XPATH), surname);		
	}
	
	public void typeEmail(String email) {
		this.clearAndSendKeys(findElement(EMAIL_INPUT_XPATH), email);		
	}
	
	public void typePassword(String password) {
		this.clearAndSendKeys(findElement(PASSWORD_INPUT_XPATH), password);		
	}
	
	public void clickGuruCheckbox() {
		this.click(findElement(GURU_CHECKBOX_XPATH));		
	}
	
	public void clickKeyContactCheckbox() {
		this.click(findElement(KEYCONTACT_CHECKBOX_XPATH));		
	}
	
	public void clickSubmitButton(){
		this.click(findElement(SUBMIT_BUTTON_XPATH));
	}
	
	public void assertSubmissionNotificationPresent(){
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(new ElementDisplayedPredicate(SUBMISSION_NOTIFICATION_DIV_XPATH));
	}

	public LoginPage closeSubmissionNotification() {
		this.click(findElement(SUBMISSION_NOTIFICATION_CLOSE_BUTTON_XPATH));
		LoginPage page = new LoginPage(driver, baseUrl);
		return page;
	}
	
	public void register(String forename, String surname, String email, String password, boolean isGuru, boolean isKeyContact){
		typeForename(forename);
		typeSurname(surname);
		typeEmail(email);
		typePassword(password);
		
		if(isGuru){
			clickGuruCheckbox();
		}
		
		if(isKeyContact){
			clickKeyContactCheckbox();
		}
		
		clickSubmitButton();
	}
}


