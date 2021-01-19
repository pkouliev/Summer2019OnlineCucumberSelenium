package com.vytrack.pages;

import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateCarPage extends BasePage {

    @FindBy(xpath = "//*[contains(@id,'LicensePlate')]")
    public WebElement licensePlateElement;

    @FindBy(name = "custom_entity_type[Driver]")
    public WebElement driverElement;

    @FindBy(name = "custom_entity_type[Location]")
    public WebElement locationElement;

    @FindBy(xpath = "//*[contains(text(),'Save and Close')]")
    public WebElement saveAndCloseButtonElement;

    @FindBy(css = "[class*='btn-success b']")
    public WebElement saveAndCloseToggle;

    @FindBy(xpath = "//*[contains(text(),'Save and New')]")
    public WebElement saveAndNewButtonElement;

    @FindBy(css = "div[id*='FuelType']")
    public WebElement fuelTypeElement;

    @FindBy(css = "input[id*='Logo']")
    // alternative: name="custom_entity_type[Logo][file]"
    // if above locators don't work, use [id^=' uniform-custom_entity_type_Logo_file'] > span[class='action']
    public WebElement logoElement;

    @FindBy(name = "custom_entity_type[ModelYear]")
    public WebElement modelYearElement;

    @FindBy(name = "custom_entity_type[Color]")
    public WebElement colorElement;


    /**
     * This method stands for selecting tags
     * Provide tag name to select
     * If checkbox already selected, it will not do anything
     *
     * @param tagName - represents aka label name has to be selected, like Junior, Senior or Purchased
     *                <p>
     *                CreateCarPage createCarPage = new CreateCarPage();
     *                <p>
     *                createCarPage.selectTags("Senior"); // Senior tag will be selected
     */
    public void selectTags(String tagName) {
        // locator for checkbox is based on label name
        // label and checkbox are siblings
        String locator = "//label[text()='" + tagName + "']/preceding-sibling::input[@type='checkbox']";
        WebElement checkBox = Driver.get().findElement(By.xpath(locator));
        BrowserUtils.waitForVisibility(checkBox, 15);
        BrowserUtils.waitForClickablility(checkBox, 15);
        if (!checkBox.isSelected()) {
            checkBox.click();
        }
    }

    /**
     * Select fuel type by visible text
     *
     * @param fuelType - Diesel, Electric, Hybrid
     *                 <p>
     *                 usage: CreateCarPage createCarPage = new CreateCarPage();
     *                 </p>
     *                 to select fuel type
     *                 createCarPage.selectFuelType("Diesel"); - if you want to select Diesel as fuel type
     */
    public void selectFuelType(String fuelType) {

        String locator = "//*[contains(@id,'FuelType')]/*[contains(text(),'" + fuelType + "')]";
        // alternative locator: //div[@class='select2-result-label' and text()='"+fuelType+"']"
        BrowserUtils.waitForClickablility(fuelTypeElement, 15);
        fuelTypeElement.click();

        WebElement fuelTypeSelectionElement = Driver.get().findElement(By.xpath(locator));
        BrowserUtils.waitForClickablility(fuelTypeSelectionElement, 15);
        fuelTypeSelectionElement.click();
    }

    /**
     * This method will upload a file
     * File from your computer!
     *
     * @param pathToFile that you want to upload file from
     */
    public void uploadLogo(String pathToFile) {
        BrowserUtils.waitForVisibility(logoElement, 15);
        WebDriverWait wait = new WebDriverWait(Driver.get(), 15);
        wait.until(ExpectedConditions.textToBePresentInElement(logoElement, logoElement.getText()));
        logoElement.sendKeys(pathToFile);
    }

    public void enterLicensePlate(String licensePlate) {
        BrowserUtils.waitForVisibility(licensePlateElement, 15);
        licensePlateElement.sendKeys(licensePlate);
    }

    public void clickSaveAndNew() {
        BrowserUtils.clickWithWait(saveAndCloseToggle);
        BrowserUtils.waitForVisibility(saveAndNewButtonElement, 5);
        BrowserUtils.clickWithWait(saveAndNewButtonElement);
    }

    public void clickSaveAndClose() {
        BrowserUtils.clickWithWait(saveAndCloseToggle);
        BrowserUtils.waitForVisibility(saveAndCloseButtonElement, 5);
        BrowserUtils.clickWithWait(saveAndCloseButtonElement);
    }
}
