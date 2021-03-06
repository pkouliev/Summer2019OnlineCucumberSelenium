package com.vytrack.pages;

import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;

//everything that is in common among pages
//can go here
//for example top menu elements don't belong to specific page
//top menu appears on every single page
//so we can keep them here
public class BasePage {

    WebDriverWait wait = new WebDriverWait(Driver.get(), 15);

    @FindBy(css = "div[class='loader-mask shown']")
    public WebElement loaderMask;

    @FindBy(css = "h1[class='oro-subtitle']")
    public WebElement pageSubTitle;

    @FindBy(css = "#user-menu > a")
    public WebElement userName;

    @FindBy(linkText = "Logout")
    public WebElement logOutLink;

    @FindBy(linkText = "My User")
    public WebElement myUser;

    @FindBy(css = "[class*='btn d']")
    public WebElement viewPerPageToggleElement;

    @FindBy(className = "dropdown-item")
    public List<WebElement> viewPerPageElements;

    @FindBy(xpath = "//th/*[contains(@class,'grid-header-cell')]")
    public List<WebElement> columnNamesElements;

    public BasePage() {
        //this method requires to provide webdriver object for @FindBy
        PageFactory.initElements(Driver.get(), this);
    }

    /**
     * While this loading screen present, html code is a not complete
     * Some elements can be missing
     * Also, you won't be able to interact with any elements
     * All actions will be intercepted
     * Waits until loader mask (loading bar, spinning wheel) disappears
     *
     * @return true if loader mask is gone, false if something went wrong
     */
    public boolean waitUntilLoaderMaskDisappear() {

        try {

            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='loader-mask shown']")));
            return true;
        } catch (NoSuchElementException e) {
            System.out.println("Loader mask not found!");
            e.printStackTrace();
            return true; // no loader mask, all good, return true
        } catch (WebDriverException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * This method stands for navigation in vytrack app
     * provide tab name, for example "Fleet" as a String
     * and module name, for example "Vehicles" as a String as well
     * then based on these values, locators will be created
     *
     * @param moduleName    provided for each test
     * @param subModuleName taken from submenu of module
     */
    public void navigateTo(String moduleName, String subModuleName) {
        String moduleLocator = "//a[normalize-space()='" + moduleName + "']";
        String subModuleLocator = "//*/span[(text()='" + subModuleName + "')]";

        //waitUntilLoaderMaskDisappear();
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(moduleLocator)));

        WebElement module = Driver.get().findElement(By.xpath(moduleLocator));
        //wait.until(ExpectedConditions.visibilityOf(module));
        waitUntilLoaderMaskDisappear();
//        wait.until(ExpectedConditions.elementToBeClickable(module));
//        wait.until(ExpectedConditions.textToBePresentInElement(module, moduleName));
//        module.click(); // once we clicked on module, submodule should be visible

        try {
            BrowserUtils.waitForClickablility(Driver.get().findElement(By.xpath(moduleLocator)), 5);
            WebDriverWait wait = new WebDriverWait(Driver.get(), 15);
            wait.until(ExpectedConditions.textToBePresentInElement(module, moduleName));
            new Actions(Driver.get()).moveToElement(module).pause(200).doubleClick(module).build().perform();
        } catch (Exception e) {
            BrowserUtils.clickWithWait(By.xpath(moduleLocator), 5);
        }

        WebElement subModule = Driver.get().findElement(By.xpath(subModuleLocator));
//        waitUntilLoaderMaskDisappear();
//        wait.until(ExpectedConditions.visibilityOf(subModule));
//        wait.until(ExpectedConditions.elementToBeClickable(subModule));
//        wait.until(ExpectedConditions.textToBePresentInElement(subModule, subModuleName));
//        subModule.click();
//        waitUntilLoaderMaskDisappear();

        try {
            BrowserUtils.waitForPresenceOfElement(By.xpath(subModuleLocator), 5);
            BrowserUtils.waitForClickablility(Driver.get().findElement(By.xpath(subModuleLocator)), 5);
            BrowserUtils.scrollToElement(Driver.get().findElement(By.xpath(subModuleLocator)));
            wait.until(ExpectedConditions.textToBePresentInElement(subModule, subModuleName));
            Driver.get().findElement(By.xpath(subModuleLocator)).click();
        } catch (Exception e) {
            BrowserUtils.waitForStaleElement(Driver.get().findElement(By.xpath(subModuleLocator)));
            BrowserUtils.clickWithTimeOut(Driver.get().findElement(By.xpath(subModuleLocator)), 5);
        }

        waitUntilLoaderMaskDisappear();

        // it waits until page is loaded and ajax calls are gone
        BrowserUtils.waitForPageToLoad(5);

    }

    /**
     * @return page name, for example: Dashboard
     */
    public String getPageSubTitle(String subTitle) {
        waitUntilLoaderMaskDisappear();
        wait.until(ExpectedConditions.textToBePresentInElement(pageSubTitle, subTitle));
        return pageSubTitle.getText().trim();
    }

    public String getUserName() {
        waitUntilLoaderMaskDisappear();
        BrowserUtils.waitForVisibility(userName, 5);
        return userName.getText();
    }

    public void logOut() {
        BrowserUtils.wait(2);
        BrowserUtils.clickWithJS(userName);
        BrowserUtils.clickWithJS(logOutLink);
    }

    public void goToMyUser() {
        waitUntilLoaderMaskDisappear();
        BrowserUtils.waitForClickablility(userName, 5).click();
        BrowserUtils.waitForClickablility(myUser, 5).click();
    }

    public void waitForPageSubtitle(String pageSubtitleText) {
        wait.until(ExpectedConditions.textToBe(By.cssSelector("h1[class='oro-subtitle']"), pageSubtitleText));
    }

    public List<String> getViewPerPageOptions() {
        BrowserUtils.waitForVisibility(viewPerPageToggleElement, 10);
        BrowserUtils.clickWithWait(viewPerPageToggleElement);
        return BrowserUtils.getListOfStrings(viewPerPageElements);
    }

    public List<String> getColumnNames() {
        waitUntilLoaderMaskDisappear();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//th/*[contains(@class,'grid-header-cell')]")));
        return BrowserUtils.getListOfStrings(columnNamesElements);
    }

}

