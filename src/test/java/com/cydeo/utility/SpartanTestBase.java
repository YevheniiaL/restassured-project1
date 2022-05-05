package com.cydeo.utility;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.reset;

public class SpartanTestBase {


    @BeforeAll
    public static void setup() {

        RestAssured.baseURI = "http://54.236.150.168:8000";
        RestAssured.basePath = "/api";

    }

    @AfterAll
    public static void teardown() {
        // in order to avoid the static value accidentally carried over
        // to different class when we practice different api ,
        // it's better if we set baseURI basePath back to it's original value using reset method
        //RestAssured.reset();
        reset();  //

    }
}
