package org.crowdguru.acceptance.page;

import org.crowdguru.acceptance.predicate.ElementDisplayedPredicate;
import org.crowdguru.datastore.domain.User;
import org.crowdguru.datastore.domain.User.Type;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;

public class RegisterPage extends Page {

	private static final String FORENAME_INPUT_XPATH = "//input[@id='inputForename']";
	
	private static final String SURNAME_INPUT_XPATH = "//input[@id='inputSurname']";
	
	private static final String EMAIL_INPUT_XPATH = "//input[@id='inputEmail']";
	
	private static final String PASSWORD_INPUT_XPATH = "//input[@id='inputPassword']";
	
	private static final String LOCATION_INPUT_XPATH = "//input[@id='inputLocation']";
	
	private static final String GURU_CHECKBOX_XPATH = "//input[@id='checkBoxGuru']";
	
	private static final String KEYCONTACT_CHECKBOX_XPATH = "//input[@name='keyContact']";
	
	private static final String SUBMIT_BUTTON_XPATH = "//input[@id='inputSubmitRegistrationForm']";
	
	private static final String SHORTPROFILE_TEXTAREA_XPATH = "//textarea[@id='textAreaShortProfile']";
	
	private static final String CAUSENAME_INPUT_XPATH = "//input[@id='inputCauseName']";
	
	private static final String SUBMISSION_NOTIFICATION_CLOSE_BUTTON_XPATH = "//div[@id='divSubmitSuccessNotification']/button";
	
	private static final String SUBMISSION_NOTIFICATION_DIV_XPATH = "//div[@id='divSubmitSuccessNotification']";
	
	public RegisterPage(WebDriver driver, String baseUrl){
		super(driver, baseUrl, "/signup");
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
	
	public void typeShortProfile(String profile) {
		this.clearAndSendKeys(findElement(SHORTPROFILE_TEXTAREA_XPATH), profile);		
	}
	
	public void typeCauseName(String name) {
		this.clearAndSendKeys(findElement(CAUSENAME_INPUT_XPATH), name);		
	}
	
	public void typeLocation(String location) {
		this.clearAndSendKeys(findElement(LOCATION_INPUT_XPATH), location);		
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

	public void closeSubmissionNotification() {
		this.click(findElement(SUBMISSION_NOTIFICATION_CLOSE_BUTTON_XPATH));
	}
	
	public void register(String forename, String surname, String email, String password, User.Type type){
		typeForename(forename);
		typeSurname(surname);
		typeEmail(email);
		typePassword(password);
		
		if(type == Type.BOTH){
			clickGuruCheckbox();
			clickKeyContactCheckbox();
		}else if (type == Type.GURU){
			clickGuruCheckbox();
		}else if(type == Type.KEYCONTACT){
			clickKeyContactCheckbox();
		}
		
		clickSubmitButton();
	}
}


