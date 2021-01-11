package com.vytrack.step_definitions;

import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hook {

    @Before
    public void setup() {
        BrowserUtils.space();
        System.out.println("Test setup!");
        Driver.getDriver().manage().window().maximize();
    }

    @After
    public void teardown(Scenario scenario) {
        // if test failed - do this
        if (scenario.isFailed()) {
            System.out.println("Test failed");
        } else {
            System.out.println("Cleanup!");
            System.out.println("Test completed!");
        }
        BrowserUtils.space();
        // after every test, we gonna close browser
        Driver.close();
    }
}
