Feature: Creating an Associate
  As a user of the API
  I want to be able to create a new associate

  Scenario: Creating a valid associate
    Given the client wants to create an associate
    When the client sends a POST request to "/associates" with valid associate data
    Then the response status code should be 201 Created
    And the response body should contain the created associate's details

  Scenario: Retrieving associates by a valid gender
    Given the client wants to retrieve associates by gender
    When the client sends a GET request to "/associates/{gender}" with a valid gender
    Then the response status code should be 200 OK
    And the response body should contain a list of associates with the specified gender
