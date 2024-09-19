
  Feature: Login to swag labs and add items to cart having a price limit

    Scenario Outline: Login and add inventory item with price limit
      Given I am on the swag labs login page
      When I enter the "<username>" and "<password>" for logging in to the swag labs
      When I click on the swag labs login button
      When the cost of the item is below "<price_limit>" it is added to cart
      Then verify cart icon has notifications
      Examples:
        | username      | password     | price_limit |
        | standard_user | secret_sauce | 10.00       |