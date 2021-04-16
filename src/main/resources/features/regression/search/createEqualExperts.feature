# new feature
# Tags: optional

@equalExperts2
Feature: Create Hotel Booking
  As a user
  I want to be able to enter hotel bookings
  So that I can see what bookings I have made

  Background: Navigate to equal Experts Hotel
    Given I am on the Hotel Start Page

  Scenario Outline: Create an hotel booking
    Given I input a "<first_name>" as my first name
    And I input a "<surname>" as my second name
    And I input a "<price>" as my price
    And I input a "<deposit>" as my deposit
    And I input a "<check-in>" as my check-in
    And I input a "<check-out>" as my check-out
    When I click on save
    Then I see a "<first_name>" of "Firstname"
    And I see a "<surname>" of "Surname"
    And I see a "<price>" of "Price"
    And I see a "<deposit>" of "Deposit"
    And I see a "<check-in>" of "Check-in"
    And I see a "<check-out>" of "Check-out"

    Examples:
      | first_name | surname | price | deposit  | check-in   | check-out  |
      | Benjamin   | Ward    | 001   | true     | 2022-11-10 | 2022-11-11 |
      | Gareth     | Farmer  | 001   | false    | 2022-12-12 | 2022-12-24 |