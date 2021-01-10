package com.vytrack.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class LoginStepDefinitions {

    // Write code here that turns the phrase above into concrete actions

    @Given("user is on the login page")
    public void user_is_on_the_login_page() {
        System.out.println("I am on the login page");
    }

    @Then("user logs in as store manager")
    public void user_logs_in_as_store_manager() {
        System.out.println("login as store manager");
     //   throw new RuntimeException("Test failed for some reason");
    }

    /**
     * And user verifies that "Dashboard" page subtitle is displayed
     *
     * @param string "Dashboard"
     *               any string in "" will become a parameter for step definition method
     */
    @Then("user verifies that {string} page subtitle is displayed")
    public void user_verifies_that_page_subtitle_is_displayed(String string) {
        System.out.println("Verifying page subtitle: " + string);
    }

    @Then("user logs in as driver")
    public void user_logs_in_as_driver() {
        System.out.println("Login as driver");
    }

    @Then("user logs in as sales manager")
    public void user_logs_in_as_sales_manager() {
        System.out.println("Login as sales manager");
    }

    /**
     * Then user enters "storemanager85" username and "wrong" password
     *
     * @param string  "storemanager85" username
     * @param string2 "wrong" password
     */
    @Then("user enters {string} username and {string} password")
    public void user_enters_username_and_password(String string, String string2) {
        System.out.println("Login with " + string + " username and " + string2 + " password");
    }

    /**
     * And user verifies that "Invalid user name or password." message is displayed
     *
     * @param string "Invalid user name or password." message
     */
    @Then("user verifies that {string} message is displayed")
    public void user_verifies_that_message_is_displayed(String string) {
        System.out.println("Verified that warning message is displayed: " + string);
    }
}
