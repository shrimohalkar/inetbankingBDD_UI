package stepDefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import pageObjects.LoginPage;

public class Stepdef extends BaseClass
{
	@Before
	public void setup() throws IOException
	{
		//Logging
		logger=Logger.getLogger("CucumberSDETProject");
		PropertyConfigurator.configure("Log4j.properties");
		logger.setLevel(Level.DEBUG);
		
		//Load properties file
		configProp= new Properties();
		FileInputStream configPropfile = new FileInputStream("config.properties");
		configProp.load(configPropfile);
		
		String br=configProp.getProperty("browser"); //getting the browser name from config.properties file
		
		//Launching browser
		if (br.equals("firefox")) {
			//System.setProperty("webdriver.gecko.driver",configProp.getProperty("firefoxpath"));
			
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		else if (br.equals("chrome")) {
			//System.setProperty("webdriver.chrome.driver",configProp.getProperty("chromepath"));
			
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		
		else if (br.equals("ie")) {
			//System.setProperty("webdriver.ie.driver",configProp.getProperty("iepath"));
			
			WebDriverManager.edgedriver().setup();
			driver = new InternetExplorerDriver();
		}
	
	}
	
	
	//........... Login steps .........
	@Given("User Launch Chrome browser")
	public void user_Launch_Chrome_browser() {
		logger.info("************* Launching Browser *****************");
		lp=new LoginPage(driver);
	}

	@When("User opens URL {string}")
	public void user_opens_URL(String url) {
	logger.info("************* Opening URL  *****************");
	driver.get(url);
	 driver.manage().window().maximize();
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_Email_as_and_Password_as(String email, String password) {
		logger.info("************* Prvding user and password *****************");
		lp.setUserName(email);
		lp.setPassword(password);
	}

	@When("Click on Login")
	public void click_on_Login() {
		logger.info("************* click on login *****************");
	   lp.clickLogin();
	}

	@Then("Page Title should be {string}")
	public void page_Title_should_be(String exptitle) throws InterruptedException {
	    
		if(driver.getPageSource().contains("Login was unsuccessful"))
		{
			logger.info("************* Login failed *****************");
			driver.close();
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertEquals(exptitle, driver.getTitle());
			logger.info("************* Login Passed *****************");
		}
		Thread.sleep(3000);
		
	}

	@When("User click on Log out link")
	public void user_click_on_Log_out_link() throws InterruptedException {
		logger.info("************* clciking on logout *****************");
		lp.clickLogout();
	    Thread.sleep(3000);
	}

	@Then("close browser")
	public void close_browser() {
		logger.info("************* cloding browser *****************");
	   driver.quit();
	}
	
	
	
}