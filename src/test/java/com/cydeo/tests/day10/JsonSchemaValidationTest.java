package com.cydeo.tests.day10;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

import com.cydeo.pojo.Spartan;
import com.cydeo.utility.SpartanTestBase;
import com.cydeo.utility.SpartanUtil;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
public class JsonSchemaValidationTest extends SpartanTestBase{

    /**
     * RestAssured make it easy to validate json response against schema file under resources folder, we need below steps :
     *
     * 1. Add dependency for JsonSchemaValidationTest
     *
     *    <dependency>
     *       <groupId>io.rest-assured</groupId>
     *       <artifactId>json-schema-validator</artifactId>
     *       <version>4.4.0</version>
     *     </dependency>
     *
     *
     * 2. Create 2 json file under resources folder
     *    1. SingleSpartanSchema.json and copy the single spartan schema from the note
     *    2. PostSpartanSchema.json and copy the post spartan response schema from the note
     * 3. Send a request to GET /spartan/{id}
     *    1. and in then section use the method provided by the dependency
     */



    @DisplayName("Testing GET/spartan/{id} response schema")
    @Test
    public void testSingleSpartanSchema(){

        int id = SpartanUtil.getRandomId();

        given().pathParam("id", id)
                .log().uri()
                .when()
                .get("/spartans/{id}")
                .then()
                .log().all()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("SingleSpartanSchema.json"));

    }




    @DisplayName("Testing POST Spartan Schema Validation")
    @Test
    public void testPostSpartanSchema(){

       Spartan payload = SpartanUtil.getRandomSpartanPOJO();



       given().contentType(ContentType.JSON)
               .body(payload)
               .log().uri()
               .when()
                       .post("/spartans")
               .then()
               .statusCode(201)
               .body(matchesJsonSchemaInClasspath("PostSpartanSchema.json"));
    }




    @DisplayName("Schema Validation of Get all Spartans")
    @Test
    public void testGetAllSpartans(){

        given().log().uri()
                .when()
                .get("spartans")
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("GetAllSpartans.json"));




    }


    @DisplayName("Schema Validation of Search Spartan")
    @Test
    public void testSearchSpartan(){


        Map<String, Object> queryMap = new LinkedHashMap<>();
        queryMap.put("nameContains", "Ea" ) ;
        queryMap.put("gender", "Male" ) ;

        given()
                .log().uri()
                .accept(ContentType.JSON).
                queryParams(queryMap)// we use it to store many query params
                .when()
                .get("/spartans/search")
               .then()//.log().all()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("SearchSpartanSchema.json"));


    }

    }
