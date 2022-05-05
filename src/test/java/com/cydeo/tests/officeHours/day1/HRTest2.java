package com.cydeo.tests.officeHours.day1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;

public class HRTest2 {

    @BeforeAll
    public static void setup(){
        baseURI ="http://34.192.175.227:1000";
        basePath="/ords/hr";
    }
    @AfterAll
    public static void tearDown(){
        // in order to avoid side effect of this static value
        // we need to reset it to the original value
        reset();
    }

    @Test
    public void getOneEmployees() {
        Response response = get("/employees/100");

        response.prettyPrint();

        System.out.println("response.path(\"first_name\") = " + response.path("first_name"));

        System.out.println("response.path(\"last_name\") = " + response.path("last_name"));

        //Assertions
        Assertions.assertEquals(200, response.statusCode());

        Assertions.assertEquals("Steven", response.path("first_name"));

    }
}
