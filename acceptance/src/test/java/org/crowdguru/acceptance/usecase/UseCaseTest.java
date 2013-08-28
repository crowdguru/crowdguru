package org.crowdguru.acceptance.usecase;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;

import javassist.compiler.NoFieldException;

import javax.management.Notification;

import org.apache.commons.io.FileUtils;
import org.crowdguru.acceptance.driver.WebDriverFactory;
import org.crowdguru.acceptance.driver.WebDriverGateway;
import org.crowdguru.acceptance.page.Navigator;
import org.crowdguru.datastore.domain.Cause;
import org.crowdguru.datastore.domain.Task;
import org.crowdguru.datastore.domain.User;
import org.crowdguru.datastore.helpers.CauseHelper;
import org.crowdguru.datastore.helpers.DatabaseTesterHelper;
import org.crowdguru.datastore.helpers.FileOperationsHelper;
import org.crowdguru.datastore.helpers.NotificationHelper;
import org.crowdguru.datastore.helpers.OfferHelper;
import org.crowdguru.datastore.helpers.TaskHelper;
import org.crowdguru.datastore.helpers.UserHelper;
import org.crowdguru.datastore.repositories.CauseRepository;
import org.crowdguru.datastore.repositories.NotificationRepository;
import org.crowdguru.datastore.repositories.OfferRepository;
import org.crowdguru.datastore.repositories.TaskRepository;
import org.crowdguru.datastore.repositories.UserRepository;
import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.IDataSet;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles({"prod"})
@ContextConfiguration({"/testContext.xml"})
public abstract class UseCaseTest {

	protected WebDriverFactory driverFactory;
	
	protected Navigator frontEnd;
	
	protected WebDriver driver;
	
	@Autowired
	protected UserRepository userRepository;
	
	@Autowired
	protected CauseRepository causeRepository;
	
	@Autowired
	protected TaskRepository taskRepository;
	
	@Autowired
	protected NotificationRepository notificationRepository;
	
	@Autowired
	protected OfferRepository offerRepository;
	
	@Autowired
	protected UserHelper userHelper;
	
	@Autowired
	protected CauseHelper causeHelper;
	
	@Autowired
	protected TaskHelper taskHelper;
	
	@Autowired
	protected NotificationHelper notificationHelper;
	
	@Autowired
	protected OfferHelper offerHelper;
	
	@Autowired
	protected FileOperationsHelper fileHelper;

	public UseCaseTest(){
		this.driverFactory = WebDriverGateway.getPhantomJSFactory();
	}
	
	@Before
	public void initializeDriver() throws Exception {
		driver = driverFactory.create();
		frontEnd = new Navigator();
		frontEnd.setDriver(driver);
	}
	
	@After
	public void quitBrowser() {
		driver.quit();
	}
	
	protected void takeScreenshot(String filename) throws IOException{
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("/tmp/" + filename));
	}
	
	@After
	public void cleanDatabase(){
		taskHelper.removeRelationships();
		userHelper.removeRelationships();
		causeHelper.removeRelationships();
		notificationHelper.removeRelationships();
		offerHelper.removeRelationships();
		
		taskRepository.deleteAllInBatch();
		causeRepository.deleteAllInBatch();
		notificationRepository.deleteAllInBatch();
		offerRepository.deleteAllInBatch();
		userRepository.deleteAllInBatch();
	}
}
