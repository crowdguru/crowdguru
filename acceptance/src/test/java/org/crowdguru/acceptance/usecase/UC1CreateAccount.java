package org.crowdguru.acceptance.usecase;

import org.crowdguru.acceptance.driver.WebDriverFactory;
import org.crowdguru.acceptance.driver.WebDriverGateway;
import org.crowdguru.acceptance.page.FrontEnd;
import org.crowdguru.acceptance.page.IndexPage;
import org.crowdguru.acceptance.page.LoginPage;
import org.crowdguru.acceptance.page.ProfilePage;
import org.crowdguru.acceptance.page.RegisterPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

/**
 * https://github.com/crowdguru/crowdguru/wiki/UC1-Create-account
 */
public class UC1CreateAccount{

	WebDriverFactory driverFactory = WebDriverGateway.getPhantomJSFactory();
	
	FrontEnd frontEnd;
	
	WebDriver driver;
	
	private static final String EMAIL = "email@email.com";
	
	private static final String PASSWORD = "pass";
	
	private static final String FORENAME = "Forename";
	
	private static final String SURNAME = "Surname";
	
	private static final String TYPE = "BOTH";
	
	@Before
	public void setUp() throws Exception {
		driver = driverFactory.create();
		frontEnd = new FrontEnd();
		frontEnd.setDriver(driver);
		
	}
	
	@Test
	public void createsAccountAndLogsOn() throws Exception {
        IndexPage indexPage = frontEnd.goHome();
        
        LoginPage loginPage = indexPage.clickLogOnButton();
        
        RegisterPage registerPage = loginPage.clickRegisterButton();
        registerPage.register(FORENAME, SURNAME, EMAIL, PASSWORD, true, true);
        registerPage.assertSubmissionNotificationPresent();
        loginPage = registerPage.closeSubmissionNotification();
        
        ProfilePage profilePage = loginPage.login(EMAIL, PASSWORD);
        
        profilePage.assertProfileField(FORENAME);
        profilePage.assertProfileField(SURNAME);
        profilePage.assertProfileField(EMAIL);
        profilePage.assertProfileField(TYPE);
	}
	
//	private void takeScreenshot(String filename) throws IOException{
//        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//        FileUtils.copyFile(screenshot, new File("/tmp/" + filename));
//	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
}
