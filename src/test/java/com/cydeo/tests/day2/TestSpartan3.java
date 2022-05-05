package com.cydeo.tests.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.reset;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestSpartan3 {


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


    @Test
    public void testGetAllSpartans() {

        Response response = get("/spartans");

        assertEquals(200, response.statusCode());
        assertEquals(ContentType.JSON.toString(), response.getContentType());
        // response.prettyPrint();

        //THE SAME ANSWER -->DIFFERENT LOGIC
        System.out.println("response.path(\"[0].gender\") = " + response.path("[0].gender"));
        System.out.println("response.path(\"gender[0]\") = " + response.path("gender[0]"));

        //Get all the id and store it into the List<Integer>
        List<Integer> ids = response.path("id");
        System.out.println("ids = " + ids);

    }


    @Test
    public void testGetXMLResponse() {

        //RestAssured use method chaining extensively to combine all part of requests
        //and verify the response in one shot
        //here since we need to provide additional header information to get xml response
        //we will start learning some method chaining to see
        //how we can provide additional information to the request

        Response response = given()
                //  .header("Accept","application/xml").
                //  .header("Accept",ContentType.XML).
                .accept(ContentType.XML). // special () for accept header
                        when().
                get("/spartans");

        response.prettyPrint();
        //write assertion

        assertEquals(200, response.statusCode());
        assertEquals(ContentType.XML.toString(), response.getContentType());//MUST USE toString()


    }


    @Test
    public void testSearch() {

        Response response =given().
                accept(ContentType.JSON).
                queryParam("nameContains","Ea").// query param
                queryParam("gender","Male").// another query param
                when().get("/spartans/search");

        response.prettyPrint();


        System.out.println("response.path(\"totalElement\") = "
                + response.path("totalElement"));

        System.out.println("response.path(\"content[0].name\") = " + response.path("content[0].name"));
        System.out.println("response.path(\"content.name[0]\") = " + response.path("content.name[0]"));

        System.out.println("response.path(\"content[1].id\") = " + response.path("content[1].id"));

       List<String> names = response.path("content.name");
        System.out.println("names = " + names);


    }




    @Test
    public void testOneSpartanPathParam(){

        Response response = given()
                .pathParam("id",1)
                        .log().uri(). // this will log everything about the request
                when()
                .get("/spartans/{id}");

        response.prettyPrint();




    }
    @Test
    public void testOneSpartanPathParam1(){

        Response response = given()
                .pathParam("id",1)
                .log().uri(). // this will log uri of the request
                        when()
                .get("/spartans/{id}");

        response.prettyPrint();




    }


}
