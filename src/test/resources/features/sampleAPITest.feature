
  Feature:  Verify POST and GET calls to the jsonplaceholder API

    Scenario Outline: to verify POST and GET calls to jsonplaceholder API
      When I make a POST request to the jsonplaceholder with "<postBody>"
      Then the resource is created and 201 status code is returned
      When I make a GET request to the jsonplaceholder with <userId>
      Then 200 status code is returned
      Then the response contains "<title>" and "<body>"
      Examples:
        |postBody                                               | userId| title | body|
        |{ \"title\": \"test\",\"body\": \"test\",\"userId\": 1}|1      |sunt aut facere|quia et|