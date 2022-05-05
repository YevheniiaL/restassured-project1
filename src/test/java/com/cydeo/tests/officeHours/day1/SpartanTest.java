package com.cydeo.tests.officeHours.day1;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;


public class SpartanTest {
    @BeforeAll
    public static void setup(){
        baseURI ="http://34.192.175.227:8000";
        basePath="/api";
    }
    @AfterAll
    public static void tearDown(){
        // in order to avoid side effect of this static value
        // we need to reset it to the original value
        reset();
    }

    @Test
    public void getAllSpartan() {
        Response response = get("/spartans");
        response.prettyPrint();

        Assertions.assertEquals(200, response.statusCode());

        Assertions.assertEquals(ContentType.JSON.toString(), response.contentType());
        //Choose the first item and give  the gender
        System.out.println("response.path(\"[0].gender\") = " + response.path("[0].gender"));
        //Choose all gender and give me first one
        System.out.println("response.path(\"gender[0]\" ) = " + response.path("gender[0]"));

        // First name

        System.out.println("response.path(\"[0].name\") = " + response.path("[0].name"));
        // Get all the name

        List<String> nameList = response.path("name");
        System.out.println("nameList.get(0) = " + nameList.get(0));

        System.out.println("nameList = " + nameList);
    }
}

