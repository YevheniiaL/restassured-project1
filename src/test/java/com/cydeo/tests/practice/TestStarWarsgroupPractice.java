package com.cydeo.tests.practice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class TestStarWarsgroupPractice {


    @BeforeAll()

        public static void setup() {

            RestAssured.baseURI = "https://swapi.dev";
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
    public void getAllFilms(){


        Response r = given().accept(ContentType.JSON).
                when().get("/films");
        r.prettyPrint();



    }

    @Test
    public void getOneFilm(){


        Response response =given().
                accept(ContentType.JSON).
                queryParam("search","Clones").// query param

                        when().get("/films/");
        response.prettyPrint();


    }

    @Test
    public void searchWithQuery(){

        Response response = given().
                queryParam("search","ca").// returns us or logic
                queryParam("search","ta").
                when().get("/people/");
        response.prettyPrint();
    }











}
