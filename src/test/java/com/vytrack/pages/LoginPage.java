package com.vytrack.pages;

import com.vytrack.utilities.ConfigurationReader;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

// according to page object model design
// we have to create corresponding page class
// for each page of application
// Login page = LoginPage class
// every page class will store web elements and methods related to that page
public class LoginPage extends BasePage {

    @FindBy(id = "prependedInput") // this line will initialize web element
    public WebElement userNameInput;

    @FindBy(id = "prependedInput2") // without @FindBy, web element will be null
    public WebElement passwordInput;

    @FindBy(id = "_submit")
    public WebElement loginButton;

    @FindBy(className = "alert alert-error")
    public WebElement warningMessage;


//    public LoginPage() {
//        // it's mandatory if you want to use @FindBy annotation
//        // this means LoginPage class
//        // Driver.getDriver() return WebDriver object
//        PageFactory.initElements(driver, this);
//    }

    String userName = ConfigurationReader.getValue("user_name");
    String password = ConfigurationReader.getValue("password");

    /**
     * reusable login method
     * just call this method to login, provide userName and password as parameters
     *
     * @param userName userName is provided within test once login method called.
     * @param password password is provided within test once login method called.
     */
    public void login(String userName, String password) {
        userNameInput.sendKeys(userName);
        // Keys.ENTER to replace login button click
        passwordInput.sendKeys(password, Keys.ENTER);
    }

    public void login() {
        userNameInput.sendKeys(userName);
        // Keys.ENTER to replace login button click
        passwordInput.sendKeys(password, Keys.ENTER);
    }

    public void login(String role) {
        switch (role) {
            case "driver" -> userName = ConfigurationReader.getValue("driver.username");
            case "store manager" -> userName = ConfigurationReader.getValue("store.manager.username");
            case "sales manager" -> userName = ConfigurationReader.getValue("sales.manager.username");
            default -> new RuntimePermission("Invalid role");
        }
        login(userName, password);
    }
}
