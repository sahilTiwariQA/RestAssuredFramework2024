package resources;

import pojo.AddPlace_Serialization;
import pojo.Location_Serialization;

import java.util.ArrayList;
import java.util.List;

public class testDataBuild {
    public AddPlace_Serialization addPlacePayload( String Address, String Language, String Name, String Phone_number, String Website)
    {
        AddPlace_Serialization ap=new AddPlace_Serialization();//created object of serialized class of getters and setters
        Location_Serialization loc = new Location_Serialization();
        loc.setLat(12.121211);
        loc.setLng(10.101011);
        ap.setLocation(loc); //Set location accepts nested JSON values, so created nested object of the class above and passed that object in this json as a value

        ap.setAccuracy(500);
        ap.setAddress(Address);
        ap.setLanguage(Language);
        ap.setName(Name);
        ap.setPhone_number(Phone_number);
        ap.setWebsite(Website);

        List<String> myListTypes = new ArrayList<>();
        myListTypes.add("shoe park");
        myListTypes.add("shop");
        myListTypes.add("Third item");
        ap.setTypes(myListTypes);
        return ap;
    }
}
