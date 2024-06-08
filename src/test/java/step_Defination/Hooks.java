package step_Defination;

import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import testing.BaseClass;
 
public class Hooks{
	public Logger logger = LogManager.getLogger(this.getClass());
	static Properties p;
	public static BaseClass bs = new BaseClass();
 
 
	@BeforeAll
	public static void getWebDriver() throws IOException{
		
		bs.Intiate_WebDriver("Windows", "chrome");
		p = BaseClass.properties;
//		logger = testing.BaseClass.Logger
	}
 
	
	@AfterAll
	public static void closeDriver() {
		bs.Close_Driver();
	}

	@AfterStep
	public void addScreenshot(Scenario scenario) {
    	WebDriver driver = new ChromeDriver();
		TakesScreenshot ts=(TakesScreenshot)driver;
    	byte[] screenshot=ts.getScreenshotAs(OutputType.BYTES);
    	scenario.attach(screenshot, "image/png",scenario.getName());

	}
}