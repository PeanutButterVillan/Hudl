# new feature
# Tags: optional
# when i search on benjamin ward, gareth farmer
# then check totals for books, conference papers, articles
# introduce a table with columns for author and number of books
@springNature
Feature: Enter Search details
  As a user
  I want to be able to enter author details
  So that I can see what they have written

  Background: Navigate to Springer Nature
    Given I am on the Springer Start Page

  Scenario Outline: Input an Author
    Given I input a "<author_name>"
    When I click on search
    Then I see a "<books>" total of "Book"
    And I see a "<conference_papers>" total of "Conference Paper"
    And I see a "<articles>" total of "Article"

    Examples:
      | author_name     | books | conference_papers | articles  |
      | Benjamin Ward   | 2     | 692               | 7,810      |
      | Gareth Farmer   | 1     | 7                 | 178       |
