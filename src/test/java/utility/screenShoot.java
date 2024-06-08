package utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class screenShoot {
	public static void takeSnapShot(WebDriver driver, String name) throws Exception{
		    
		    
		    try {
	            TakesScreenshot ts = (TakesScreenshot)driver;
	            File source = ts.getScreenshotAs(OutputType.FILE);
	            String destinationPath = (System.getProperty("user.dir") + "\\screenshots\\" + name +".png");
	            Files.copy(source.toPath(), new File(destinationPath).toPath(), StandardCopyOption.REPLACE_EXISTING);
	            System.out.println("Screenshot captured: " + destinationPath);
	        } catch (IOException e) {
	            System.out.println("Exception while taking screenshot: " + e.getMessage());
	        }
		   
	}

}
