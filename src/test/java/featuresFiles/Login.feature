Feature: Login
  PonyClub user login
  Scenario: As a PonyClub user I want to Login successfully
    Given I am on home page
    And I go to login page
    And I should see Login page
    And I enter valid login details, username as "gvenkatesh@trillium.net" and password as "Password1"
    Then login must be successful.
