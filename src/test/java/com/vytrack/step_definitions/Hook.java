package com.vytrack.step_definitions;

import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hook {

    @Before
    public void setup() {
        BrowserUtils.space();
        System.out.println("Test setup!");
        //Driver.get().manage().window().maximize();
    }

    @After
    public void teardown(Scenario scenario) {
        // if test failed - do this
        if (scenario.isFailed()) {
            System.out.println("Test failed");
            byte[] screenshot = ((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failed Test Screenshot");
        } else {
            System.out.println("Cleanup!");
            System.out.println("Test completed!");
        }
        BrowserUtils.space();
        // after every test, we gonna close browser
        Driver.close();
    }
}
