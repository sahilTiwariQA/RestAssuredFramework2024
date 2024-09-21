package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace_Serialization;
import pojo.Location_Serialization;
import resources.APIResources;
import resources.Utils;
import resources.testDataBuild;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class MyStepdefs extends Utils {
    RequestSpecification req;
    ResponseSpecification resSpec;
    Response response;
    static String place_id;

    testDataBuild TDB = new testDataBuild();

    @Given("Add place payload {string} {string} {string} {string} {string}")
    public void add_place_payload(String Address, String Language, String Name, String Phone_number, String Website) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        req= given().spec(requestSpecificationMethod()).body(TDB.addPlacePayload(Address,Language,Name,Phone_number,Website));
    }

    @When("User calls {string} API with {string} HTTP request")
    public void user_calls_api_with_post_http_request(String resource, String httpMethod) {
        // Write code here that turns the phrase above into concrete actions
        //Constructor will be called with value of resource which you pass
        APIResources apiRes= APIResources.valueOf(resource);
        System.out.println("URL from API Resources ENUM: "+apiRes.getResource());

        resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        if(httpMethod.equalsIgnoreCase("POST")) {
            response = req.when().post(apiRes.getResource());
        }
        else if (httpMethod.equalsIgnoreCase("GET")) {
            response = req.when().get(apiRes.getResource());
        }
    }
    @Then("The response call is success with Status code {int}")
    public void the_response_call_is_success_with_status_code(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(200, response.getStatusCode());
    }

    @Then("{string} parameter in response body is {string}")
    public void parameter_in_response_body_is(String keyParam, String valueParam) {
        assertEquals(getJsonPath(response,keyParam), valueParam);
    }

    @Then("Verify place_id created maps to {string} using {string}")
    public void Verify_place_id_created_maps_to_using_getPlaceAPI(String name, String resource) throws IOException {
        //prepare a request spec
        place_id = getJsonPath(response, "place_id");
        req= given().spec(requestSpecificationMethod()).queryParam("place_id",place_id);
        user_calls_api_with_post_http_request(resource, "GET");
        String actualName = getJsonPath(response, "name");
        assertEquals(actualName, name);

    }

    @Given("DeletePlace Payload")
    public void delete_place_payload() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        req = given().spec(requestSpecificationMethod()).body(TDB.deletePlaceAPI(place_id));
    }

}
