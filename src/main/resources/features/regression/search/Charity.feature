@All @CrossBrowser
Feature: Enter Charity details
  As a user
  I want to be able my charity details
  So that I can complete the Task list for my registration

  Background: Navigate to the demo app
    Given I am on the Service Start Page
    And I create a new account

   @devops @fullregression
  Scenario Outline: Input an invalid charity number
    Given I create a "charity" account and reach the task list
    When I input "<charity_number>" for my charity number registered in "<country>"
    Then I see a "<error_message>" error on the "<field_name>" field

    Examples:
      | charity_number | country          | field_name            | error_message           |
      | 123456789      | England          | charityNumberEngWales | CHARITY_LENGTH_ENGLAND  |
      | SC123456789    | Scotland         | charityNumberScot     | CHARITY_LENGTH_SCOTLAND |
      | NIC123456789   | Northern Ireland | charityNumberNI       | CHARITY_LENGTH_NI       |

 @devops  @fullregression
  Scenario Outline: Create a charity business account
    Given I create a business account for charity
    When I enter details for a new charity named "Sample Charity" registered in "<country>" with charity_number
    And I check my details and enrol the account

    Examples:
      | country          |
      | England          |
      | Scotland         |
      | Northern_Ireland |

   @devops @fullregression
  Scenario Outline: Create a charity business account with manual address entry
    Given I create a business account for charity
    When I manually enter details for a new charity named "Sample Charity" registered in "<country>" with charity_number
    And I check my details for charity and enrol the account

    Examples:
      | country          |
      | England          |
      | Scotland         |
      | Northern_Ireland |

  @devops @fullregression
  Scenario: Input an invalid charity name
    Given I create a "charity" account and reach the task list
    When I leave charity name empty
    Then I see a "CHARITY_NAME_EMPTY" error on the "charityName" field


