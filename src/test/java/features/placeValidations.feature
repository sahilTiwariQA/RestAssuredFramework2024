Feature: Validating Place API's

  @AddPlace
  Scenario Outline: Verify if place is being successfully added ded using AddPlaceAPI
    Given Add place payload "<Address>" "<language>" "<name>" "<Phone_number>" "<Website>"
    When User calls "AddPlaceAPI" API with "POST" HTTP request
    Then The response call is success with Status code 200
    And "status" parameter in response body is "OK"
    And "scope" parameter in response body is "APP"
    And Verify place_id created maps to "<name>" using "getPlaceAPI"

    Examples:
      | Address | language | name      | Phone_number  | Website          |
      | Kalyan  | English  | Ghatotkac | +91 123456789 | www.google.com   |
#      | Thane   | Spanish  | Ghatot    | +91 123456787 | www.google.co.in |

  @DeletePlace
    Scenario: Verify if Delete Place API is working
      Given DeletePlace Payload
      When User calls "deletePlaceAPI" API with "POST" HTTP request
      Then The response call is success with Status code 200
      And "status" parameter in response body is "OK"
