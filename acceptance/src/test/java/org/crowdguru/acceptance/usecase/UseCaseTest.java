package org.crowdguru.acceptance.usecase;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;

import org.apache.commons.io.FileUtils;
import org.crowdguru.acceptance.driver.WebDriverFactory;
import org.crowdguru.acceptance.driver.WebDriverGateway;
import org.crowdguru.acceptance.page.FrontEnd;
import org.crowdguru.datastore.helpers.DatabaseTesterHelper;
import org.crowdguru.datastore.helpers.FileOperationsHelper;
import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.DataSetException;
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
	
	protected FrontEnd frontEnd;
	
	protected WebDriver driver;
	
	@Autowired
	protected FileOperationsHelper fileHelper;

	@Autowired
	protected DatabaseTesterHelper databaseTester;
	
	public UseCaseTest(){
//		this.driverFactory = WebDriverGateway.getPhantomJSFactory();
		this.driverFactory = WebDriverGateway.getFirefoxFactory();
	}
	
	@Before
	public void initializeDriver() throws Exception {
		driver = driverFactory.create();
		frontEnd = new FrontEnd();
		frontEnd.setDriver(driver);
	}
	
	protected void loadDataSet(String fileName) throws DatabaseUnitException, SQLException, Exception{
		IDataSet dataset = fileHelper.loadFromFlatXmlFile(fileName);
		databaseTester.cleanInsert(dataset);
	}
	
	@After
	public void cleanDatabase() throws SQLException, Exception{
		databaseTester.clean();
	}
	
	@After
	public void quitBrowser() {
		driver.quit();
	}
	
	
	protected void takeScreenshot(String filename) throws IOException{
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("/tmp/" + filename));
	}
	
	
}
