package org.crowdguru.acceptance.page;

import org.openqa.selenium.WebDriver;

public class CreateTaskPage extends Page {

	public CreateTaskPage(WebDriver driver, String baseUrl) {
		super(driver, baseUrl, "/tasks/new");
	}
}
