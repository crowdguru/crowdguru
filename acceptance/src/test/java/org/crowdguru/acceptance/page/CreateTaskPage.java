package org.crowdguru.acceptance.page;

import org.openqa.selenium.WebDriver;
import org.crowdguru.acceptance.page.BrowseTasksPage;

public class CreateTaskPage extends Page {

	private static final String TITLE_INPUT_XPATH = "//input[@name='title']";
	
	private static final String SHORTDESC_INPUT_XPATH = "//textarea[@name='shortDescription']";
	
	private static final String LONGDESC_INPUT_XPATH = "//textarea[@name='longDescription']";
	
	private static final String AMOUNT_SELECTION_XPATH = "//select[@name='amount']";
	
	private static final String UNIT_SELECTION_XPATH = "//select[@name='unit']";
	
	private static final String SECOND_SPECIALISM_GROUP_XPATH = "//a[contains(@href, '#specialism3')]";
	
	private static final String SECOND_SPECIALISM_XPATH = "//div[@id='specialism3']/label[2]/input";
	
	private static final String SUBMIT_BUTTON_XPATH = "//button[@id='buttonSave']";
	
	//select[@id='duration']
	
	public CreateTaskPage(WebDriver driver, String baseUrl) {
		super(driver, baseUrl, "/tasks/new");
	}

	public void inputTitle(String title) {
		this.clearAndSendKeys(findElement(TITLE_INPUT_XPATH), title);
	}

	public void inputShortDescription(String description) {
		this.clearAndSendKeys(findElement(SHORTDESC_INPUT_XPATH), description);
	}
	
	public void inputLongDescription(String description) {
		this.clearAndSendKeys(findElement(LONGDESC_INPUT_XPATH), description);
	}
	
	public void selectAmount(Integer amount) {
		this.select(findElement(AMOUNT_SELECTION_XPATH), amount.toString());
	}

	public void selectUnit(String unit) {
		this.select(findElement(UNIT_SELECTION_XPATH), unit);
	}

	public void selectSpecialismGroup() {
		this.click(findElement(SECOND_SPECIALISM_GROUP_XPATH));
	}
	
	public void selectSpecialism() {
		this.click(findElement(SECOND_SPECIALISM_XPATH));
	}

	public BrowseTasksPage clickSubmitButton() {
		this.click(findElement(SUBMIT_BUTTON_XPATH));
		BrowseTasksPage page = new BrowseTasksPage(driver, baseUrl);
		return page;
	}
}
