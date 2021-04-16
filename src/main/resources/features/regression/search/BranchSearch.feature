@costcutter
Feature: CostCutter search

  Scenario: Finding a York branch

    Given I am on the Costcutter search page
    When I search for "York"
    Then the page should contain "York"