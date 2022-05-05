package com.cydeo.tests.officeHours.day2;

import com.cydeo.utility.HrORDSTestBase;

import com.cydeo.tests.officeHours.day2.*;
import com.cydeo.pojo.Location;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class P5_DeSerialization extends HrORDSTestBase {

    /**
     * Create a test called getLocation
     * 1. Send request to GET /Location
     * 2. log uri to see
     * 3. Get all Json as a map and print out screen all the things with the help of  map
     * System.out.println("====== GET FIRST LOCATION  ======");
     * System.out.println("====== GET FIRST LOCATION LINKS  ======");
     * System.out.println("====== GET ALL Location AS LIST OF MAP======");
     * System.out.println("====== FIRST LOCATION ======");
     * System.out.println("====== FIRST LOCATION ID ======");
     * System.out.println("====== FIRST LOCATION COUNTRY_ID ======");
     * System.out.println("====== GET FIRST LOCATION FIRST LINK  ====== ");
     * System.out.println("====== LAST LOCATION ID ======");
     */

    @Test
    public void getLocationWithMap() {

        JsonPath jsonPath = given().log().uri().
                when().get("/Location").prettyPeek().jsonPath();

        System.out.println("====== GET FIRST LOCATION  ======");
        Map<String, Object> firstLocationMap = jsonPath.getMap("items[0]");
        System.out.println("firstLocationMap = " + firstLocationMap);

        System.out.println("====== GET FIRST LOCATION LINKS  ======");
        Map<String, Object> firstLocationLinks = jsonPath.getMap("items[0].links[0]");
        System.out.println("firstLocationLinks = " + firstLocationLinks);
        System.out.println("firstLocationLinks.get(\"rel\") = " + firstLocationLinks.get("rel"));
        System.out.println("firstLocationLinks.get(\"href\") = " + firstLocationLinks.get("href"));

        System.out.println("====== GET ALL Location AS LIST OF MAP======");
        List<Map<String, Object>> allLocation = jsonPath.getList("items");
        System.out.println("allLocation = " + allLocation);

        System.out.println("====== FIRST LOCATION ======");
        System.out.println("allLocation.get(0) = " + allLocation.get(0));


        System.out.println("====== FIRST LOCATION ID ======");
        System.out.println("allLocation.get(0).get(\"location_id\") = " + allLocation.get(0).get("location_id"));


        System.out.println("====== FIRST LOCATION COUNTRY_ID ======");
        System.out.println("allLocation.get(0).get(\"county_id\") = " + allLocation.get(0).get("county_id"));


        System.out.println("====== GET FIRST LOCATION FIRST LINK  ====== ");
        System.out.println("allLocation.get(0).get(\"links\") = " + allLocation.get(0).get("links"));
        List<Map<String, String>> links = (List<Map<String, String>>) allLocation.get(0).get("links");
        System.out.println("links.get(0).get(\"rel\") = " + links.get(0).get("rel"));
        System.out.println("links.get(0).get(\"href\") = " + links.get(0).get("href"));


        System.out.println("====== LAST LOCATION ID ======");
        System.out.println("allLocation.get(allLocation.size() - 1).get(\"location_id\") = " + allLocation.get(allLocation.size() - 1).get("location_id"));


    }

    /**
     * Create a test called getLocation
     * 1. Send request to GET /Location
     * 2. log uri to see
     * 3. Get all Json as a Location Object and print out screen all the things with the help of  POJO
     * System.out.println("====== GET FIRST LOCATION  ======");
     * System.out.println("====== GET FIRST LOCATION LINKS AS MAP ======");
     * System.out.println("====== ALL Location ======");
     * System.out.println("====== FIRST LOCATION ======");
     * System.out.println("====== FIRST LOCATION ID ======");
     * System.out.println("====== FIRST LOCATION COUNTRY_ID ======");
     * System.out.println("====== GET FIRST LOCATION FIRST LINK  ====== ");
     * System.out.println("====== LAST LOCATION ID ======");
     */
    @Test
    public void getLocationWithPOJO() {


        JsonPath jsonPath = given().log().uri().
                when().get("/Location").prettyPeek().jsonPath();


        List<Location> allLocation = jsonPath.getList("items", Location.class);


        System.out.println("====== GET FIRST LOCATION  ======");
        System.out.println("allLocation.get(0) = " + allLocation.get(0));

        System.out.println("====== GET FIRST LOCATION LINKS AS MAP ======");
        System.out.println("allLocation.get(0).getLinks().get(0).get(\"rel\") = " + allLocation.get(0).getLinks().get(0).get("rel"));
        System.out.println("allLocation.get(0).getLinks().get(0).get(\"href\") = " + allLocation.get(0).getLinks().get(0).get("href"));


        System.out.println("====== GET FIRST LOCATION LINKS AS POJO ======");
        System.out.println("allLocation.get(0).getLinksObject().get(0).getHref() = " + allLocation.get(0).getLinksObject().get(0).getHref());
        System.out.println(allLocation.get(0).getLinksObject().get(0).getRel());

        System.out.println("====== ALL Location ======");
        System.out.println("allLocation = " + allLocation);

        System.out.println("====== FIRST LOCATION ID ======");
        System.out.println("allLocation.get(0).getLocationId() = " + allLocation.get(0).getLocationId());

        System.out.println("====== FIRST LOCATION COUNTRY_ID ======");
        System.out.println("allLocation.get(0).getCountryId() = " + allLocation.get(0).getCountryId());

        System.out.println("====== LAST LOCATION ID ======");
        System.out.println("allLocation.get(allLocation.size()-1) = " + allLocation.get(allLocation.size() - 1));


    }
}