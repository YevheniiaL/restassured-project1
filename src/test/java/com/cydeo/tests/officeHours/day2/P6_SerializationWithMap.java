package com.cydeo.tests.officeHours.day2;
import com.cydeo.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(OrderAnnotation.class)
public class P6_SerializationWithMap extends SpartanTestBase {
    /**
     * We will do workflow for spartan E2E
     *
     * POST One Spartan
     * get ID from this POST request
     * and use it for PUT and PATCH request
     * GET One Spartan to verify it is updated
     * DELETE same spartan
     * Get same spartan again to see 404
     */
    static int spartanId;

    @Order(1)
    @Test
    public void PostSpartan() {

        Map<String,Object> newSpartan=new HashMap<>();

        newSpartan.put("name","John Doe");
        newSpartan.put("gender", "Male");
        newSpartan.put("phone",6548526973l);


        spartanId = given().log().all()
                .contentType(ContentType.JSON)
                .body(newSpartan)
                .when().post("/spartans").prettyPeek().
                then().body("success", is("A Spartan is Born!"))
                .extract().jsonPath().getInt("data.id");


        System.out.println("spartanId = " + spartanId);


    }
    @Order(2)
    @Test
    public void PutSpartan() {

        Map<String,Object> newSpartan=new HashMap<>();

        newSpartan.put("name","Kimberly");
        newSpartan.put("gender", "Male");
        newSpartan.put("phone",6548521173l);


        given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("id", spartanId)
                .body(newSpartan)
                .when().put("/spartans/{id}").prettyPeek().
                then().statusCode(204);


    }
    @Order(3)
    @Test
    public void PatchSpartan() {

        Map<String,Object> newSpartan=new HashMap<>();

        newSpartan.put("name","Elon");



        given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("id", spartanId)
                .body(newSpartan)
                .when().patch("/spartans/{id}").prettyPeek().
                then().statusCode(204);


    }
    @Order(4)
    @Test
    public void GetSpartan() {


        JsonPath jsonPath = given().log().all()
                .pathParam("id", spartanId)
                .when().get("/spartans/{id}").prettyPeek().
                then().statusCode(200)
                .extract().jsonPath();

        Assertions.assertEquals(spartanId, jsonPath.getInt("id"));
    }
    @Order(5)
    @Test
    public void DeleteSpartan() {


        given().log().all()
                .pathParam("id", spartanId)
                .when().delete("/spartans/{id}").prettyPeek().
                then().statusCode(204);

    }
    @Order(6)
    @Test
    public void GetSpartanAfterDelete() {


        given().log().all()
                .pathParam("id", spartanId)
                .when().get("/spartans/{id}").prettyPeek().
                then().statusCode(404);
    }
    }
