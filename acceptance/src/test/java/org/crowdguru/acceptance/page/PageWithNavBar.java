package org.crowdguru.acceptance.page;

import static org.junit.Assert.assertTrue;

import org.crowdguru.acceptance.predicate.PageLoadedPredicate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageWithNavBar extends Page{

	private static final String CREATE_TASK_LINK_XPATH = "//a[contains(@href, '/tasks/new')]";
	
	private static final String BROWSE_TASKS_LINK_XPATH = "//a[contains(@href, '/tasks')]";
	
	public PageWithNavBar(WebDriver driver, String baseUrl, String path) {
		super(driver, baseUrl, path);
	}
	
	public CreateTaskPage clickCreateTaskLink(){
		this.click(findElement(CREATE_TASK_LINK_XPATH));
		CreateTaskPage page = new CreateTaskPage(driver, baseUrl);
		return page;
	}
	
	public BrowseTasksPage clickBrowseTasksLink(){
		this.click(findElement(BROWSE_TASKS_LINK_XPATH));
		BrowseTasksPage page = new BrowseTasksPage(driver, baseUrl);
		return page;
	}
}