@hudl
Feature: Complete Login Form

  Scenario Outline: Attempt to login successfully

    Given I am on the hudl login page
    When I enter my email "<email>"
    And I input "<password>" as my password and submit
    Then the page should contain a search field

    Examples:
      | email                   | password       |
      | hdcbrownhill@gmail.com  | CityScreen101  |

  Scenario Outline: Attempt to login unsuccessfully

    Given I am on the hudl login page
    When I enter my email "<email>"
    And I input "<password>" as my password and submit
    Then the page should contain a password field

    Examples:
      | email                   | password       |
      | hdcbrownhill@gmail.com  | cityscreen101! |
      | hdcbrownhill@gmail.com  | CityScreen202! |

