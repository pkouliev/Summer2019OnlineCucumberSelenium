Feature: Login
  As user, I want to be able to login into vytrack
  under different roles with username and password

# Any step that was implemented before, can be reused
# If test step has yellow background, that means it doesn't have implementation yet
# click command/ctrl + option/alt + L to organize code, save like in java
# Can have multiple scenarios in every feature file,
# but better organize same steps in one scenario and
# just keep passing parameters in "some word" so to reuse test steps.
# keep scenarios short.

  Background:
    Given user is on the login page

  @store_manager
  Scenario: Login as store manager
    Then user logs in as store manager
    And user verifies that "Dashboard" page subtitle is displayed

  @driver
  Scenario: Login as driver
    Then user logs in as driver
    And user verifies that "Dashboard" page subtitle is displayed

  @sales_manager
  Scenario: Login as sales manager
    Then user logs in as sales manager
    And user verifies that "Dashboard" page subtitle is displayed

  @negative_test
  Scenario: Verify that warning message is displayed when password is not correct
    Then user enters "storemanager85" username and "wrong" password
    And user verifies that "Invalid user name or password." message is displayed

  @negative_test @driver
  Scenario: Verify that warning message is displayed when password is not correct
    Then user enters "wrong_username" username and "UserUser123" password
    And user verifies that "Invalid user name or password." message is displayed

  @driver_with_data_table
  Scenario: Login as driver (data table example)
    Then user logs in as driver with following credentials
      | username | user160     |
      | password | UserUser123 |
    And user verifies that "Quick Launchpad" page subtitle is displayed



