package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {
@Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        //write a code to taht will give place id
        //execute this code only when place id is null
        MyStepdefs MSD = new MyStepdefs();
        if (MyStepdefs.place_id==null) {
            MSD.add_place_payload("1 Chi St", "Chinese", "Chan", "5665556677", "www.kl.chi");
            MSD.user_calls_api_with_post_http_request("AddPlaceAPI", "POST");
            MSD.Verify_place_id_created_maps_to_using_getPlaceAPI("Chan", "getPlaceAPI");
        }
    }

}
