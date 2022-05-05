package com.cydeo.tests.day7;


import com.cydeo.pojo.Place;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class ZipCode_Api_Test {


    @BeforeAll
    public static void setup() {
        baseURI = "https://api.zippopotam.us";
        basePath = "/us";
    }

    @AfterAll
    public static void teardown() {
        reset();
    }

    /**
     * Create a Parameterized Test to check
     * using given zipcodes 22030 , 10019 , 12345 , 10000, 19152
     * send out request to
     * GET https://api.zippopotam.us/us/{zip}
     * test the status code is 200
     */


    @ParameterizedTest
    // @ValueSource(ints = {22030 , 10019 , 12345 , 10000, 19152}) // we can provide it here
    @CsvFileSource(resources = "/zipcode.csv") // or read it from csv file
    public void testZipToCityDDT(int zipParam) {

        given().log().uri().pathParam("zip", zipParam)
                .when()
                .get("/{zip}")
                .then()
                .statusCode(is(200));
        System.out.println("zipParam = " + zipParam);


    }


    @Test
    public void testZipToCity() {


        //https://api.zippopotam.us/us/22030
        // log your request , provide 22030 as path variable
        // send get request and assert status is 200
        // assert response is in json format
        // assert "country": "United States",
        // assert "state": "Virginia"


        given().pathParam("zip", 22030).log().uri()
                .when()
                .get("/{zip}")
                .then()
                .log().all()
                .statusCode(is(200))
                .contentType(ContentType.JSON)
                .body("country", is("United States"))
                .body("places[0].state", is("Virginia"))
                .body("'post code'", equalTo("22030"))
                .body("places[0].'place name'", is("Fairfax"));
        //we use '' single quote, because we have space in the name of "postal code"


    }


    /**
     * Homework1 :
     * in one test
     * send request to GET https://api.zippopotam.us/us/va/fairfax
     * log request all parts
     * use va and fairfax as path variables with name state / city
     * send get request verify
     * status code 200 , json format
     **/

    @Test
    public void testCityToZip() {

        given().pathParam("state", "va").pathParam("city", "arlington")
                .log().uri()
                .when()
                .get("/{state}/{city}")
                .then().log().all()
                .statusCode(is(200))
                .contentType(is(ContentType.JSON));
    }


    /**
     * Homework2:
     * <p>
     * send same request and store the response object
     * get JsonPath object from the response
     * print last place name
     * print all zip codes after storing it into the list
     * create a pojo called Place to represent place json object
     * with these specific fields :
     * - name as String
     * - postCode as int
     * - latitude as float
     * - longitude as float
     * {
     * "place name": "Fairfax",
     * "longitude": "-77.3242",
     * "post code": "22030",
     * "latitude": "38.8458"
     * }
     * de-serialize the first response into Place pojo and print it out
     * save all the place json array into List<Place> and print it out.
     */

    @DisplayName("Test City to zip with POJO")
    @Test
    public void testCityToZipWithPOJO() {


        JsonPath jp = given().pathParam("state", "fl").pathParam("city", "bonita springs")
                .log().uri()
                .when()
                .get("/{state}/{city}")
                .then().statusCode(is(200)).extract().jsonPath();


        //No POJO yet

        String lastPlaceName = jp.getString("places[-1].'place name'");
        System.out.println("lastPlaceName = " + lastPlaceName);

        assertThat(lastPlaceName, equalTo("Bonita Springs"));

        List<Object> allZipCodes = jp.getList("places.'post code'");
        System.out.println("allZipCodes = " + allZipCodes);

        assertThat(allZipCodes.size(), is(6));


        //With POJO
        Place firstPlace = jp.getObject("places[0]", Place.class);
        System.out.println("firstPlace = " + firstPlace);

        List<Place> allPlaces = jp.getList("places", Place.class);
        System.out.println("allPlaces = " + allPlaces);



    }
}
