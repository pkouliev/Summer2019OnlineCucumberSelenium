package com.vytrack.pages;

import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VehiclesPage extends BasePage {

    @FindBy(xpath = "//*[@title='Create Car']")
    public WebElement createCarElement;

    @FindBy(xpath = "//*[text()='License Plate']")
    public WebElement licensePlateLabel;

    /**
     * Use this method to click on "Create Car" button
     * Method already contains waits to handle synchronization issues
     */
    public void clickToCreateCar() {
        WebDriverWait wait = new WebDriverWait(Driver.get(), 15);
        BrowserUtils.waitForVisibility(createCarElement, 15);
        BrowserUtils.waitForClickablility(createCarElement, 15);
        wait.until(ExpectedConditions.textToBePresentInElement(createCarElement, "Create Car"));
        createCarElement.click();
    }
}
