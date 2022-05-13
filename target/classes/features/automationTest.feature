@Test
Feature: Selenium Automation Test

  Scenario: verify the prices of dresses and add the highest price to cart
    Given open browser and navigate to url
    When click on dresses and get the prices of all dresses displayed
    Then add the dress with highest price to the cart