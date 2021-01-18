package com.vytrack.step_definitions;

import com.vytrack.pages.CreateCarPage;
import com.vytrack.pages.VehiclesPage;
import io.cucumber.java.en.Then;

import java.util.List;
import java.util.Map;

public class CreateCarStepDefinitions {

    CreateCarPage createCarPage = new CreateCarPage();
    VehiclesPage vehiclesPage = new VehiclesPage();

    @Then("user click on {string} button")
    public void user_click_on_button(String string) {
        if (string.equals("Create Car")) {
            vehiclesPage.clickToCreateCar();
        }

    }

    //    Then user adds new car information:
//            | License Plate | Driver      | Location        | Model Year | Color |
//            | TestPlates    | Test Driver | Washington D.C. | 2020       | Black |
//      dataTable.get(0).get("Model Year") = 2020
//      .get(0) - means get data from first row (excluding column names or header)
//      .get("Model Year") - get value of Model Year
    @Then("user adds new car information:")
    public void user_adds_new_car_information(List<Map<String, String>> dataTable) {
        // as many rows of data we have, it will create cars
        System.out.println(dataTable.size());
        int row = 1;
        for (Map<String, String> map : dataTable) {
            createCarPage.enterLicensePlate(map.get("License Plate"));
            createCarPage.driverElement.sendKeys(map.get("Driver"));
            createCarPage.locationElement.sendKeys(map.get("Location"));
            createCarPage.modelYearElement.sendKeys(map.get("Model Year"));
            createCarPage.colorElement.sendKeys(map.get("Color"));

            if (row == dataTable.size()) {
                // if it's a last row - click save and close
                createCarPage.clickSaveAndClose();
            } else {
                // if it's not the last row - click save and add new
                createCarPage.clickSaveAndNew();
            }
            System.out.println(map);
            row++;
        }
    }
}
