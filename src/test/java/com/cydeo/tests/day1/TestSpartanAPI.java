package com.cydeo.tests.day1;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

@Tag("smokeAll")
public class TestSpartanAPI {


    @Test
    public void testHello() {

        System.out.println("Hello world");

        //send request and get and save the response
        //GET http://107.21.187.92:8000/api/hello

        Response response = get("http://107.21.187.92:8000/api/hello");
        response.prettyPrint();
        System.out.println("response.statusCode() = " + response.statusCode());

        response.prettyPrint();// to get response body( the easiest way)

        Assertions.assertEquals(200, response.statusCode());

    }






}











