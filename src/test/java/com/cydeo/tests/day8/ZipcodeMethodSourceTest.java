package com.cydeo.tests.day8;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class ZipcodeMethodSourceTest {


    /**
     * Write a method that can return all zipCodes for Fairfax VA
     * send request to GET https://api.zippopotam.us/us/va/fairfax
     * and returns List<String>
     * <p>
     * Write a parameterized Test to test each zip code if Fairfax va
     * send request to GET https://api.zippopotam.us/us/{zip}
     * use @MethodSource
     */


    @BeforeAll
    public static void setup() {
        baseURI = "https://api.zippopotam.us/";
        basePath = "/us";
    }

    @AfterAll
    public static void teardown() {
        reset();
    }


    @ParameterizedTest(name = "Getting one zipcode {0}")// we use it to name our parametrized test; {0} - first argument
    @MethodSource("getAllZipcodes")
    public void testAllZipcodes(String zipParam) {
        System.out.println("zipParam = " + zipParam);

        given().log().uri().pathParam("zip", zipParam)
                .when()
                .get("/{zip}")
                .then().statusCode(is(200));

    }


    /**
     * Method to return all zipcode of Fairfax
     */
    public static List<String> getAllZipcodes() {

        List<String> allZips = get("/va/fairfax")
                .path("places.'post code'");
        return allZips;


    }


}
