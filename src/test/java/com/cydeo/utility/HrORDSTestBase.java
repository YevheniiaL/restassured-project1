package com.cydeo.utility;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.reset;

public class HrORDSTestBase {


    @BeforeAll
    public static void setup() {

        RestAssured.baseURI = "http://54.236.150.168:1000";
        RestAssured.basePath = "/ords/hr";
        DB_Util.createConnection();



    }

    @AfterAll
    public static void teardown() {
        // in order to avoid the static value accidentally carried over
        // to different class when we practice different api ,
        // it's better if we set baseURI basePath back to it's original value using reset method
        //RestAssured.reset();
        reset();  //
        DB_Util.destroy();

    }
}
