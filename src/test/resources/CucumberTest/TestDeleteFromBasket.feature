
Feature: Busket

  Scenario: Add product and delete then
    When Go to the shop
    And Add '3' first product in basket
    And Go to the basket
    And Delete all products
    Then There no items
