
  Feature:  Verify POST and GET calls to the jsonplaceholder API

    Scenario: to verify POST and GET calls to jsonplaceholder API
      When I make a POST request to the jsonplaceholder
      Then the resource is created and 201 status code is returned
      When I make a GET request to the jsonplaceholder
      Then 200 status code is returned