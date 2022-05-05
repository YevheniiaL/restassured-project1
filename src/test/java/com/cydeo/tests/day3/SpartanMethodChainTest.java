package com.cydeo.tests.day3;

import com.cydeo.utility.SpartanTestBase;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SpartanMethodChainTest extends SpartanTestBase {


    @Test
    public void getOneSpartanTest() {

                 given().
                     pathParam("id", 1).
                     log().all().
                     accept(ContentType.JSON).
                when().
                     get("/spartans/{id}").
                then()
                         .statusCode(is(200)) //asserting status code 200c
                       // header("Content-Type",ContentType.JSON.toString())
                        // header("Content-Type","application/json")
                         .contentType(ContentType.JSON) // last 3 lines return the same value
                         // .body("id",equalTo(1))
                         // .body("name",is("OliverTheKing")); - can be separated or together
                         .body("id",equalTo(1),"name",is("OliverTheKing"));





    }

    @Test
    public void testSearch(){

        // http://54.236.150.168:8000/api/spartans/search?nameContains=Ea&gender=Male

        given().queryParam("nameContains","Ea")
                .queryParam("gender","Male").
                log().everything().
                when()
                        .get("/spartans/search")
                .then()
                .log().all()
                .statusCode(is(200))
                .contentType(ContentType.JSON.toString())
                .body("totalElement",is(3))
                .body("content",hasSize(3))
                .body("content.name", hasItem("Sean"))
                .body("content.gender",everyItem(is("Male")))
                .body("content.name",everyItem(containsStringIgnoringCase("ea")));

    }




}
