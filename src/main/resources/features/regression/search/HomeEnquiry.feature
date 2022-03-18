@policyexpert
Feature: Complete Home Enquiry Form

  Scenario Outline: Complete First Three Entries

    Given I am on the home enquiry page
    When I enter a title "<title>"
    And I enter first name "<first_name>"
    And I input "<last_name>" as my second name
    And I input "<dob>" as my birth day
    Then the page should contain "Policy"

    Examples:
      | title      | first_name       | last_name             | dob         |
      | Mr         | First Name Test  | Last Name Test        | 12/March/199  |

