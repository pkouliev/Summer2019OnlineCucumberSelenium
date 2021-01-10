package com.vytrack.step_definitions;

import com.vytrack.utilities.BrowserUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hook {

    @Before
    public void setup() {
        BrowserUtils.space();
        System.out.println("Test setup!");
    }

    @After
    public void teardown() {
        System.out.println("Cleanup!");
        System.out.println("Test completed");
        BrowserUtils.space();
    }
}
