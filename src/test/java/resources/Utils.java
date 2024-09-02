package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {
    RequestSpecification reqSpec;
    public RequestSpecification requestSpecificationMethod() throws IOException
    {
        PrintStream log = new PrintStream(new FileOutputStream("logger.txt"));
        reqSpec = new RequestSpecBuilder().setBaseUri(getGlobalProps("baseURL"))
                .addQueryParam("key","qaclick123")
                .addFilter(RequestLoggingFilter.logRequestTo(log))
                .addFilter(ResponseLoggingFilter.logResponseTo(log))
                .setContentType(ContentType.JSON).build();
        return reqSpec;
    }

    //creating a method to fetch global.properties values
    public static String getGlobalProps(String key) throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("/Users/sahiltiwari/IdeaProjects/RestAssuredFramework2024/src/test/java/resources/global.properties");
        prop.load(fis);
        return prop.getProperty(key);
    }
}
