package com.vytrack.step_definitions;

import com.vytrack.pages.AllCalendarEventsPage;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.List;

public class AllCalendarEventsPageStepDefinitions {

    AllCalendarEventsPage allCalendarEvents = new AllCalendarEventsPage();

    @Then("user gets list of options from view per page dropdown")
    public void user_gets_list_of_options_from_dropdown() {
        System.out.println(allCalendarEvents.getViewPerPageOptions());
    }

    @Then("user verifies that column names are displayed")
    public void user_verifies_that_column_names_are_displayed(List<String> dataTable) {
        System.out.println(dataTable);
        Assert.assertEquals(dataTable, allCalendarEvents.getColumnNames());
    }
}
