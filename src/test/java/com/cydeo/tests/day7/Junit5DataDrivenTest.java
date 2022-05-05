package com.cydeo.tests.day7;

import com.cydeo.pojo.Place;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class Junit5DataDrivenTest {


    @ParameterizedTest
    @ValueSource(strings = {"Furkan", "Abbos", "Yevheniia", "Shazia", "Tugba", "Mohamed", "Kimberley" })
    public void testNameLength(String eachName) {

        assertTrue(eachName.length() > 3, "Name " + eachName + " has less than 10 characters");


    }


    ///reading from csv file under resources
    @ParameterizedTest
    @CsvFileSource(resources = "/people.csv", numLinesToSkip = 1) // provide the name of file, and skip line 1 (headers)
    public void testPerson(String name, String gender, long phone) {

        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);


    }

    /*
    1. Create a csv file called `math.csv` under `resource` folder
    2. add 3 columns `num1` ,   `num2` , `expectedResult`
    3. add valid data for addition num1 + num2 = expectedResult
    4. create a `@ParameterizedTest` with above `CsvFileSource` , skip the header
    5. assert addition num1 + num2 = expectedResult
    6. make sure to change the number and see failed tests.
     */

    @DisplayName("Test addition with provided expected result from math.csv file")
    @ParameterizedTest
    @CsvFileSource(resources = "/math.csv", numLinesToSkip = 1)
    public void testAddition(int num1, int num2, int expectedResult) {

        assertEquals(expectedResult, num1 + num2);

    }


    /*
     *  Create a file called state_city.csv under resources folder
     *  it has two columns  state , city
     *  add some valid data
     *
     *  send request to GET https://api.zippopotam.us/us/{state}/{city}
     *      *   log request uri
     *      *   use state and city as path variables with name state / city
     *      *   for actual value of path params get it from csv file
     *      *   send get request verify
     *      *   status code 200 , json format
     */

    @DisplayName("DDT  of ZipCodes with POJO and csv file")
    @ParameterizedTest
    @CsvFileSource(resources = "/state_city.csv", numLinesToSkip = 1)
    public void testZipcodeDDT(String state, String city) {

        JsonPath jp = given().pathParam("state", state).pathParam("city", city)
                .log().uri()
                .when()
                .get("https://api.zippopotam.us/us/{state}/{city}")
                .then().contentType(is(ContentType.JSON.toString()))
                .statusCode(is(200)).extract().jsonPath();


        List<Integer> allZipCodes = jp.getList("places.'post code'", Integer.class);
        System.out.println("allZipCodes = " + allZipCodes);


        List<Place> allPlaces = jp.getList("places", Place.class);
        for (Place each : allPlaces) {
            System.out.println(each.getName() + " " + each.getPostCode());

        }

    }


}
