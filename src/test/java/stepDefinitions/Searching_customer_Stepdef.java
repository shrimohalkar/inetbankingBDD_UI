package stepDefinitions;

import org.junit.Assert;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.SearchCustomerPage;

public class Searching_customer_Stepdef extends BaseClass {

	//Searching customer by email ID.............................
		@When("Enter customer EMail")
		public void enter_customer_EMail() {
			searchCust=new SearchCustomerPage(driver);
			logger.info("********* Searching customer details by Email **************");
			searchCust.setEmail("victoria_victoria@nopCommerce.com");
		}

		@When("Click on search button")
		public void click_on_search_button() throws InterruptedException {
			searchCust.clickSearch();
			Thread.sleep(3000);
		}

		@Then("User should found Email in the Search table")
		public void user_should_found_Email_in_the_Search_table() {
			boolean status=searchCust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
			Assert.assertEquals(true, status);
		}
		
		//steps for searching a customer by Name................
			@When("Enter customer FirstName")
			public void enter_customer_FirstName() {
				logger.info("********* Searching customer details by Name **************");
				searchCust=new SearchCustomerPage(driver);
				searchCust.setFirstName("Victoria");
			}

			@When("Enter customer LastName")
			public void enter_customer_LastName() {
				searchCust.setLastName("Terces");
			}

			@Then("User should found Name in the Search table")
			public void user_should_found_Name_in_the_Search_table() {
				boolean status=searchCust.searchCustomerByName("Victoria Terces");
				Assert.assertEquals(true, status);
			}
		
}
