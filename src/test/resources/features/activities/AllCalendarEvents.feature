@all_calendar_events
Feature: All calendar events

  Background: open login page and login as store manager
    Given user is on the login page
    When user logs in as store manager
    And user navigates to "Activities" then to "Calendar Events"

  Scenario: Get list of options from "view per page" dropdown
    Then user gets list of options from view per page dropdown

  Scenario: Verify column names
    Then user verifies that column names are displayed
      | TITLE             |
      | CALENDAR          |
      | START             |
      | END               |
      | RECURRENT         |
      | RECURRENCE        |
      | INVITATION STATUS |


