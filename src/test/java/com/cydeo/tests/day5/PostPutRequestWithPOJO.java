package com.cydeo.tests.day5;

import com.cydeo.pojo.Spartan;
import com.cydeo.utility.SpartanTestBase;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.cydeo.utility.SpartanUtil;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class PostPutRequestWithPOJO extends SpartanTestBase {

/**
 * POST /spartans
 * content type is json
 * body is
 * {
 * "name":"API POST",
 * "gender":"Male",
 * "phone":1231231231
 * }
 *
 * ---We learnt how to represent json body using map
 *
 * ---Another common way of representing  json data is using
 * special purpose Java Object created from A class that have fields matched to json keys
 * and have getters and setters
 * This type of Obj , purpose is to represent data,
 * known as POJO(Plain Old Java Obj)
 * The class to create POJO known as POJO class ,
 * It's used for creating POJO, just like you create any other obj
 *
 * e.g.:In order to represent json data with 3 fields:
 * name, gender, phone
 * we can create java class with 3 instant fields with the same name
 * */


    /**
     * given()
     * .log().all()
     * .contentType(ContentType.JSON)
     * .body(bodyMap).
     * when()
     * .post("/spartans").
     * then()
     * .log().all()
     * .statusCode(is(201));
     */


    @Test
    public void testPostWithPOJOasBody() {

        Spartan sp1 = new Spartan("B23 POJO", "Male", 45367287654l);
        System.out.println("sp1 = " + sp1);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(sp1).
                when()
                .post("/spartans").
                then()
                .log().all()
                .statusCode(is(201));


    }

    // HOMEWORK 1: Create a method under SpartanUtil class
    // called getRandomSpartanPOJO()
    // return Spartan object with randomized field values

    @Test
    public void testGetRandomSpartan() {

        //get random id
        int randomId = SpartanUtil.getRandomId();

        System.out.println("randomId = " + randomId);


        given().pathParam("id", randomId).log().uri()
                .when()
                .get("/spartans/{id}")
                .then().log().body()
                .statusCode(is(200));


    }


    // HOMEWORK 2: Use POJO for Update 1 Data PUT Request
    // use your getRandomSpartanPOJO utility method for body
    @Test
    public void testUpdateRandomSpartan() {

        //get a random id
      int randomId = SpartanUtil.getRandomId();

      //send GET request and save it in jsonPath
        JsonPath jp = given().pathParam("id", randomId).log().all()
                .when()
                .get("/spartans/{id}").jsonPath();

        //get the value of Jason body with random id
        //save it in map
        Map<String, Object> original = jp.getMap("");
        System.out.println("original = " + original);


        //send PUT request to update body with random id , make sure --> 204
        given().pathParam("id", randomId).log().all().contentType(ContentType.JSON)
                .body(SpartanUtil.getRandomSpartanPOJO())
                .when()
                .put("/spartans/{id}").
                then().log().body()
                .statusCode(is(204));

        //get the updated json body with random id
        JsonPath jp2 = given().pathParam("id", randomId).log().all().contentType(ContentType.JSON)
                .when()
                .get("/spartans/{id}").jsonPath();


        //save it in map
        Map<String, Object> updated = jp2.getMap("");
        System.out.println("updated = " + updated);

        //compare data before and after update (PUT)
        Assertions.assertNotEquals(original,updated);


    }


}
