Feature: Validating Place API's

  Scenario: Verify if place is being successfully added ded using AddPlaceAPI
    Given Add place payload
    When User calls "AddPlace" API with POST HTTP request
    Then The response call is success with Status code 200
    And "status" parameter in response body is "OK"
    And "scope" parameter in response body is "APP"

