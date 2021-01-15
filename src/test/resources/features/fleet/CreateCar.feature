@create_car
Feature: Create Car

  Background:
    Given user is on the login page
    And user logs in as store manager
    Then user navigates to "Fleet" then to "Vehicles"
    And user click on "Create Car" button

  Scenario: Create new car
    Then user adds new car information:
      | License Plate | Driver      | Location         | Model Year | Color |
      | TestPlates    | Test Driver | Washington, D.C. | 2020       | Black |
      | SuperMan      | Cool Driver | Houston TX       | 2019       | Red   |