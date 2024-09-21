package resources;
//enum is a special class in Java which has collection of constants or methods
public enum APIResources {
    AddPlaceAPI("maps/api/place/add/json"),
    getPlaceAPI("maps/api/place/get/json"),
    deletePlaceAPI("maps/api/place/delete/json");

    private String resourceGlobalVar;

    APIResources(String resource) {
        this.resourceGlobalVar=resource;
    }

    public String getResource()
    {
        return resourceGlobalVar;
    }
}
