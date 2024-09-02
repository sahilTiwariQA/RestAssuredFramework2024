Feature: Validating Place API's

  Scenario Outline: Verify if place is being successfully added ded using AddPlaceAPI
    Given Add place payload "<Address>" "<language>" "<name>" "<Phone_number>" "<Website>"
    When User calls "AddPlace" API with POST HTTP request
    Then The response call is success with Status code 200
    And "status" parameter in response body is "OK"
    And "scope" parameter in response body is "APP"

    Examples:
      | Address | language | name      | Phone_number  | Website        |
      | Kalyan  | English  | Ghatotkac | +91 123456789 | www.google.com |
