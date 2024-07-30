
Feature: opening Hubspot Page and sign in to Hubspot

  Scenario: Launching Hubspot and sign in
    Given I launch the url for hubspot
    When I click on the login link
    Then I am taken to the sign in page "email field is present"
    And The sign in page is asserted
  Scenario: enter username and password
    Then I enter the username and password
    And I click on the login button
    Then I should be asked OTP for login
  Scenario: Fetching otp from gmail and entering on hubspot
    Given I am on gmail
    When I fetch otp and enter in hubspot
    Then I am back on hubspot homepage


#  Scenario Outline: Launching Hubspot and sign in
#    Given I launch the url for hubspot
#    When I click on the login link <invalid user>, <invalid pwd>
#Examples:
#    |invalid user|invalid pwd|
#    |            |           |