package org.crowdguru.acceptance.page;

import org.crowdguru.acceptance.predicate.ElementDisplayedPredicate;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TaskPage extends PageWithNavBar {

	private static final String OFFER_HELP_BUTTON_XPATH = "//a[@id='buttonOfferHelp']";
	
	private static final String SEND_OFFER_BUTTON_XPATH = "//button[@id='buttonSendOffer']";
	
	public TaskPage(WebDriver driver, String baseUrl, Long taskId){
		super(driver, baseUrl, "/tasks/" + taskId);
	}
	
	public void clickOfferHelpButton() {
		this.click(findElement(OFFER_HELP_BUTTON_XPATH));
	}
	
	public void clickSendOfferButton() {
		this.click(findElement(SEND_OFFER_BUTTON_XPATH));
	}
	
	public void assertOfferHelpModalPresent(){
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(new ElementDisplayedPredicate(SEND_OFFER_BUTTON_XPATH));
	}
}


