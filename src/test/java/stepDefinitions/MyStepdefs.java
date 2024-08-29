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
import resources.Utils;
import resources.testDataBuild;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class MyStepdefs extends Utils {
    RequestSpecification req;
    ResponseSpecification resSpec;
    Response response;
    testDataBuild TDB = new testDataBuild();

    @Given("Add place payload")
    public void add_Place_Payload() throws FileNotFoundException {
        req= given().spec(requestSpecificationMethod()).body(TDB.addPlacePayload());
    }

    @When("User calls {string} API with POST HTTP request")
    public void user_calls_api_with_post_http_request(String string) {
        // Write code here that turns the phrase above into concrete actions
        resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        response = req.when().post("maps/api/place/add/json").then().spec(resSpec).extract().response();
    }
    @Then("The response call is success with Status code {int}")
    public void the_response_call_is_success_with_status_code(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(200, response.getStatusCode());
    }

    @Then("{string} parameter in response body is {string}")
    public void parameter_in_response_body_is(String keyParam, String valueParam) {
        String responseBody = response.asString();
        JsonPath js = new JsonPath(responseBody);
        assertEquals(js.get(keyParam).toString(), valueParam);
    }

}
