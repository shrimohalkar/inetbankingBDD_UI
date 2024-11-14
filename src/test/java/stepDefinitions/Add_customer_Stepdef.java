package stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.By;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.AddcustomerPage;

public class Add_customer_Stepdef extends BaseClass {
	
	//Customer feature step definitions..........................................
	
		@Then("User can view Dashboad")
		public void user_can_view_Dashboad() {
		  
			addCust=new AddcustomerPage(driver);
			logger.info("********* Verifying Dashboad page title after login successful **************");
			Assert.assertEquals("Dashboard / nopCommerce administration",addCust.getPageTitle());
		}

		@When("User click on customers Menu")
		public void user_click_on_customers_Menu() throws InterruptedException {
			Thread.sleep(3000);
			logger.info("********* Clicking on customer main menu **************");
			addCust.clickOnCustomersMenu();
		}

		@When("click on customers Menu Item")
		public void click_on_customers_Menu_Item() throws InterruptedException {
			Thread.sleep(2000);
			logger.info("********* Clicking on customer sub menu **************");
			addCust.clickOnCustomersMenuItem();
		}

		@When("click on Add new button")
		public void click_on_Add_new_button() throws InterruptedException {
			  addCust.clickOnAddnew();
			  Thread.sleep(2000);
		}

		@Then("User can view Add new customer page")
		public void user_can_view_Add_new_customer_page() {
			 Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());
		}

		@When("User enter customer info")
		public void user_enter_customer_info() throws InterruptedException {
			String email = randomestring() + "@gmail.com";
			addCust.setEmail(email);
			addCust.setPassword("test123");
			// Registered - default
			// The customer cannot be in both 'Guests' and 'Registered' customer roles
			// Add the customer to 'Guests' or 'Registered' customer role
			addCust.setCustomerRoles("Guest");
			Thread.sleep(3000);

			addCust.setManagerOfVendor("Vendor 2");
			addCust.setGender("Male");
			addCust.setFirstName("Pavan");
			addCust.setLastName("Kumar");
			addCust.setDob("07/05/1985"); // Format: DD/MM/YYY
			addCust.setCompanyName("busyQA");
			addCust.setAdminContent("This is for testing.........");
		}

		@When("click on Save button")
		public void click_on_Save_button() throws InterruptedException {
			logger.info("********* Saving customer details **************");   
			addCust.clickOnSave();
			   Thread.sleep(2000);
		}

		@Then("User can view confirmation message {string}")
		public void user_can_view_confirmation_message(String string) {
			Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
					.contains("The new customer has been added successfully"));
		}


}
