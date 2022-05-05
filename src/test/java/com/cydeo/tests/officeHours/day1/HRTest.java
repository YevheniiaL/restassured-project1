package com.cydeo.tests.officeHours.day1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HRTest {
    @Test
    public void simpleGetRequest() {

        Response response = RestAssured.get("http://34.192.175.227:1000/ords/hr/regions");

        System.out.println(response.headers());

        System.out.println("================");

        response.prettyPrint();

        System.out.println("================");

        System.out.println("response.statusCode() = " + response.statusCode());

        System.out.println("================");

        System.out.println("response.header(\"Date\") = " + response.header("Date"));

        System.out.println("================");

        // verify data is exist
        System.out.println(response.headers().hasHeaderWithName("Date"));


    }

    @Test
    public void getOneEmployees() {

        Response response = RestAssured.get("http://34.192.175.227:1000/ords/hr/employees/100");

        response.prettyPrint();

        System.out.println("response.path(\"first_name\") = " + response.path("first_name"));

        System.out.println("response.path(\"last_name\") = " + response.path("last_name"));

        //Assertions
        Assertions.assertEquals(200, response.statusCode());

        Assertions.assertEquals("Steven", response.path("first_name"));


    }
}
