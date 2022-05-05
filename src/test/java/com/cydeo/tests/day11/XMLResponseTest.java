package com.cydeo.tests.day11;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
public class XMLResponseTest {

    @BeforeAll
    public static void setUp() {

        baseURI = "http://ergast.com" ;
        basePath= "api/f1";
    }


    @AfterAll
    public static void tearDown() {

        reset();
    }


    @DisplayName("Test GET /drivers/{driverId} xml response")
    @Test
    public void testSingleXMLResponse(){

       given()
                .pathParam("driverId", "abate")
                .log().uri()
                .when()
                .get("/drivers/{driverId}")
                .then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.XML)

       .body("MRData.DriverTable.Driver.GivenName", is ("Carlo")) //xmlPath
               .body("MRData.DriverTable.Driver.FamilyName",is("Abate") )
               .body("MRData.DriverTable.Driver.DateOfBirth",is("1932-07-10") )
               .body("MRData.DriverTable.Driver.Nationality",is("Italian") );

    }


    @DisplayName("Test GET /drivers/{driverId} extract xml element text")
    @Test
    public void testSingleDriverXMLResponse2() {

        Response response = given()
                .pathParam("driverId", "abate")
                .log().uri().
                when()
                .get("/drivers/{driverId}");

        XmlPath xp = response.xmlPath();
        String firstName = xp.getString("MRData.DriverTable.Driver.GivenName");
        System.out.println("firstName = " + firstName);
        String lastName = xp.getString("MRData.DriverTable.Driver.FamilyName");
        System.out.println("lastName = " + lastName);

        //what is the XMLPath to get attribute of an element
        //if your element tag is Person and attribute is gender
        //here is the xmlPath to get the attribute Person.@gender
        //TagName.@attributeName syntax

        String  driverIdAttribute = xp.getString("MRData.DriverTable.@driverId");//@-->attribute
        System.out.println("driverIdAttribute = " + driverIdAttribute);

        String urlAttribute = xp.getString("MRData.DriverTable.Driver.@url");
        System.out.println("urlAttribute = " + urlAttribute);

    }



    @DisplayName("Test GET /drivers xml response")
    @Test
    public void getAllDriverXMLResponse(){

        XmlPath xp =  get("/drivers").xmlPath()  ;
        String firstDriverName = xp.getString( "MRData.DriverTable.Driver[0].GivenName") ;
        System.out.println("firstDriverName = " + firstDriverName);

        //get all Given names of all Drivers
        List<String> allGivenNames = xp.getList("MRData.DriverTable.Driver.GivenName", String.class);
        System.out.println("allGivenNames = " + allGivenNames);

        List<String> allDriverIds = xp.getList("MRDate.DriverTable.Driver.@driverId",String.class);
        System.out.println("list = " + allDriverIds);

        // Homework , Create a method to get RaNdom valid driverId
        // so you can pass to the test /drivers/{driverId}  instead of guessing valid driver id

        // Explore the rest of the Collection
        // for example :
        // http://ergast.com/api/f1/2021/drivers
        // practice getting xml element text value , xml element attributes
        // same goes for the rest of the collection.

        // Homework 2 :
        // Send this request in Movie API get xml response by using query param r=xml
        // GET http://www.omdbapi.com/?t=The Mandalorian&r=xml?apiKey=YourKeyGoesHere

        // print out all the movie attributes

        // Send request GET http://www.omdbapi.com/?s=The Mandalorian&r=xml?apiKey=YourKeyGoesHere
        // verify root element attribute  totalResults="11"
        // store all the titles on List<String> and print.
        // verify the size of list match the attribute totalResults="11"


        // Homework 3 :
        // Send request to  GET /spartans provide accept header to get XML response
        // practice getting the value of xml elements for single elements
        // or list of elements


        // Homework 4 :
          /*
            Send GET Request to https://vpic.nhtsa.dot.gov/api/vehicles/GetMakeForManufacturer/988?format=xml
            and verify the
            count element text is 2
            message Results returned successfully
            first Make_ID is  474 , Make_Name Honda
            */
    }


}