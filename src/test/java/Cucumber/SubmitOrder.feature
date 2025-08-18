@tag
Feature: Purchase the Order from Ecommerce Website

Background:
Given I landed on Ecomerce Page


@tag2
  Scenario: Positive Test of Submitting the order
    Given Logged in with username <username> and with password <password>
    When I add product <productName> from Cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is disaplyed on ConfirmationPage.
    
     Examples:
      | username				   | password   	| productName |
      | truptidoke008@gmail.com    | Vikroli@123    | ZARA COAT 3 |