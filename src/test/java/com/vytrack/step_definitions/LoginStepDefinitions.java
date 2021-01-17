package com.vytrack.step_definitions;

import com.vytrack.pages.LoginPage;
import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.Map;

public class LoginStepDefinitions {

    // Write code here that turns the phrase above into concrete actions
    LoginPage loginPage = new LoginPage(); // created login page object

    @Given("user is on the login page")
    public void user_is_on_the_login_page() {
        System.out.println("I am on the login page");
        Driver.get().get(ConfigurationReader.getValue("url"));
    }

    @Then("user logs in as store manager")
    public void user_logs_in_as_store_manager() {
        System.out.println("login as store manager");
        // we read username and password from properties file
        // usually in java we use camel case for naming variables
        String userName = ConfigurationReader.getValue("user_name");
        String password = ConfigurationReader.getValue("password");
        loginPage.login(userName, password);
    }

    /**
     * And user verifies that "Dashboard" page subtitle is displayed
     *
     * @param string "Dashboard"
     *               any string in "" will become a parameter for step definition method
     */
    @Then("user verifies that {string} page subtitle is displayed")
    public void user_verifies_that_page_subtitle_is_displayed(String string) {
        System.out.println(string);
        Assert.assertEquals(string, loginPage.getPageSubTitle(string));
        System.out.println("Verifying page subtitle: " + string);
    }

    @Then("user logs in as driver")
    public void user_logs_in_as_driver() {
        System.out.println("Login as driver");
    }

    @Then("user logs in as sales manager")
    public void user_logs_in_as_sales_manager() {
        System.out.println("Login as sales manager");
        // throw new RuntimeException("Test failed just because!");
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
        loginPage.login(string, string2);
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

    @Then("user logs in as driver with following credentials")
    public void user_logs_in_as_driver_with_following_credentials(Map<String, String> dataTable) {
        System.out.println(dataTable);
        loginPage.login(dataTable.get("username"), dataTable.get("password"));
    }

    @Then("user logs in as {string}")
    public void user_logs_in_as(String role) {
        loginPage.login(role);
    }

    @Then("the page title should be {string}")
    public void the_page_title_should_be(String title) {
        BrowserUtils.waitForPageTitle(title);
        Assert.assertEquals("Title is incorrect", title, Driver.get().getTitle());
    }
}
