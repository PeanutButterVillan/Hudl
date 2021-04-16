# new feature
# Tags: optional

@equalExperts
Feature: Create Hotel Booking
  As a user
  I want to be able to delete hotel bookings
  So that I can see what bookings I have removed

  Background: Navigate to equal Experts Hotel
    Given I am on the Hotel Start Page

  Scenario Outline: Delete an hotel booking
    Given I identify a "<first_name>" of "Firstname"
    And I identify a "<surname>" of "Surname"
    And I identify a "<price>" of "Price"
    When I click on delete
    #Then I do not see a "<first_name>" of "Firstname"
    #And I do not see a "<surname>" of "Surname"
    #And I do not see a "<price>" of "Price"

    Examples:
      | first_name | surname | price |
      | Gareth     | Farmer  | 001   |
