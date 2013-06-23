package org.crowdguru.webapp.views;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.os.CommandLine;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.phantomjs.PhantomJSDriverService.Builder;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
* Tests base class.
* Takes care of initialising the Remote WebDriver
*/
public abstract class BaseTest {
    private WebDriver driver = null;

    private static final String CONFIG_FILE = "src/test/resources/ghostdriver.properties";

    protected static Properties config;
    protected static DesiredCapabilities caps;
    private static String phantomjsExecPath;
    private static String phantomjsLogLevel;
    private static String phantomjsLogFile;
    private static String baseURL;

    @BeforeClass
    public static void configure() throws IOException {
        // Read config file
    	config = new Properties();
    	config.load(new FileReader(CONFIG_FILE));

        // Prepare capabilities
    	caps = new DesiredCapabilities();
    	caps.setJavascriptEnabled(true);
    	caps.setCapability("takesScreenshot", true);
    	
    	System.setProperty("console.logging.level", loadProperty("console_logging_level"));
    	System.setProperty("logging.aspect", loadProperty("logging_aspect"));
    	System.setProperty("logging.directory", loadProperty("logging_directory"));
    	System.setProperty("env", loadProperty("env"));
    	
    	//Prepare builder configurations
    	phantomjsExecPath = loadProperty("phantomjs_exec_path");
    	phantomjsLogLevel = loadProperty("phantomjs_driver_log_level");
    	phantomjsLogFile = loadProperty("phantomjs_driver_log_file");
    	baseURL = loadProperty("base_url");
    }
    
    private static String loadProperty(String fieldKey){
    	return config.getProperty(fieldKey);
    }

    @SuppressWarnings("deprecation")
	@Before
    public void prepareDriver() throws Exception
    {
    	Builder builder = new PhantomJSDriverService.Builder()
    		.withLogFile(new File(phantomjsLogFile))
    		.usingCommandLineArguments(new String[]{
    				"--webdriver-loglevel=" + phantomjsLogLevel
    		});
    	
    	try{
    		builder = builder.usingPhantomJSExecutable(new File(phantomjsExecPath));
    	}catch(IllegalStateException e){
    		if(e.getMessage().contains("The driver executable does not exist")){
    			//Fall back if executable not set. Search in the $PATH variable.
    			phantomjsExecPath = CommandLine.find("phantomjs");
    			builder = builder.usingPhantomJSExecutable(new File(phantomjsExecPath));
    		}else{
    			throw e;
    		}
    	}
    	
    	driver = new PhantomJSDriver(builder.build(), caps);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().setSize(new Dimension(1024, 768));
    }

    @After
    public void quitDriver() {
    	driver.quit();
    }
    
    protected WebDriver getDriver() {
        return driver;
    }
    
    protected String getBaseURL(){
    	return baseURL;
    }
}
