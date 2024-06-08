package step_Defination;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.BasePage;
import pageObject.Doctor_Details;
import pageObject.Surgeries;
import pageObject.Workplace_Health;
import testing.BaseClass;
import utility.DriverSetup;



public class project {
	
	WebDriver driver;
	Doctor_Details dd;
	Surgeries sr;
	Workplace_Health wh;
	

	@Given("get City and get Doctor type")
	public void get_city_and_get_doctor_type() throws InterruptedException {
		driver = DriverSetup.getWebDriver();
		driver.get("https://www.practo.com/");
	    dd = new Doctor_Details(driver);
	    dd.Search();
	    
	   //Assert.assertEquals(driver.getTitle(),"Best Dentists Near Me In Bangalore - Instant Appointment Booking, View Fees, Feedbacks | Practo");
	}

	@When("Filter the Doctor as per Requirement")
	public void filter_the_doctor_as_per_requirement() throws InterruptedException {
	    dd.get_Doctors();
	}

	@Then("get Doctor Names,  Expierence, and Fees")
	public void get_doctor_names_expierence_and_fees() throws InterruptedException {
	    dd.print_Doctor_Names();
	}

	@When("click on Surgeries")
	public void click_on_surgeries() {
		sr = new Surgeries(driver);
	    System.out.println(driver.getTitle());
	    Assert.assertEquals(driver.getTitle(), "Practo Care Surgeries | End to end care from top surgeons in your city");
	}

	@Then("print all the Surgies names")
	public void print_all_the_surgies_names() {
	   sr.print_Surgeries_Name();
	}

	@When("click the Workplace Health")
	public void click_the_workplace_health() {
	   sr.Select_Health_Wellness_Plans();
	   wh = new Workplace_Health(driver);
	   
	}

	@When("enter the Wrong Details")
	public void enter_the_wrong_details() throws InterruptedException {
	   wh.enter_Wrong_Details();
	   Assert.assertEquals(driver.getTitle(),"Employee Health | Corporate Health & Wellness Plans | Practo");
	}

	@Then("check the submit button is diaplayed or not")
	public void check_the_submit_button_is_diaplayed_or_not() throws InterruptedException {
		  Assert.assertEquals(wh.btn_NotDisplay.isDisplayed(), true);
		  wh.clear_All_Fields();
	}

	@When("enter the Correct Details")
	public void enter_the_correct_details() throws InterruptedException {
	    wh.enter_Correct_Details();
	    wh.get_ThankYou_Message();
	   
	}

	@Then("check the ThankYou message")
	public void check_the_thank_you_message() throws InterruptedException {
		 wh.get_ThankYou_Message();
		 driver.quit();
	}
	
	@AfterStep
    public void addScreenshot(Scenario scenario) {
        
    	// this is for cucumber junit report
        	
        	TakesScreenshot ts=(TakesScreenshot) driver;
        	byte[] screenshot=ts.getScreenshotAs(OutputType.BYTES);
        	scenario.attach(screenshot, "image/png",scenario.getName());
        	            
        
      
    }

}
