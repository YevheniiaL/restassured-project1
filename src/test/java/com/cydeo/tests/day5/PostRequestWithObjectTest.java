package com.cydeo.tests.day5;

import com.cydeo.utility.SpartanTestBase;
import com.cydeo.utility.SpartanUtil;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * Serialization: procces of Converting from Java Obj to Json (or any other text)
 * DE-SERIALIZATION:
 * Process of Converting from Json (any text) at Java Obj
 */

public class PostRequestWithObjectTest extends SpartanTestBase {

    /**
     * POST /spartans
     * content type is json
     * body is
     * {
     * "name":"API POST",
     * "gender":"Male",
     * "phone":1231231231
     * }
     * <p>
     * instead of providing above body in String ,
     * We want to be able to provide body as java object(like map or POJO)
     * and let any kind of Serialization library convert that into String for us
     * while sending the request
     **/


    @Test
    public void testPostWithMap() {

        Map<String, Object> bodyMap = new LinkedHashMap<>();
        bodyMap.put("name", "API POST");
        bodyMap.put("gender", "Male");
        bodyMap.put("phone", 1231231231);

        System.out.println("bodyMap = " + bodyMap);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(bodyMap).
                when()
                .post("/spartans").
                then()
                .log().all()
                .statusCode(is(201));


    }


    @Test
    public void testPostWithMapFaker() {

        //Create random data with Faker
        //optionally :create a utility out of it

//        Faker faker = new Faker();
//
//        Map<String, Object> bodyMap = new LinkedHashMap<>();
//        bodyMap.put("name", faker.name().firstName());
//        bodyMap.put("gender", faker.demographic().sex()); //Male or Female
//        bodyMap.put("phone", faker.number().numberBetween(5000000000L,9999999999l));  // get number between 5000000000-99999999999
//
//        System.out.println("bodyMap = " + bodyMap);




        /*
        // having utility, so we can just call a method as below
        // 1. create a class under utility package with name SpartanUtil
        // 2. create a public static method with return type of Map<String, Object>
        // 3. add above code you already wrote for method body and return the bodyMap from the method
        // 4. call the method here to get the random body

         */


        Map<String,Object> bodyMap = SpartanUtil.GetRandomSpartanMapBody();

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(bodyMap).
                when()
                .post("/spartans").
                then()
                .log().all()
                .statusCode(is(201));


    }


    @Test
    public void testPutWithRandomMapBody(){

        // instead of guessing id, let's get last json object id

        int lastId = get("/spartans/").path("id[-1]");
        System.out.println("lastId = " + lastId);

        //prepare updated body
        Map<String ,Object> updatedBodyMap = SpartanUtil.GetRandomSpartanMapBody();
        given()
                .log().all()
                .pathParam("id",lastId)
                .contentType(ContentType.JSON)
                .body(updatedBodyMap)
                .when()
                .put("spartans/{id}")
                .then().log().all()
                .statusCode(is(204));

        given().contentType(ContentType.JSON).log().all().pathParam("id",lastId)
                .when().get("/spartans/{id}")
                .then().log().all()
                .statusCode(is(200));


    }

}
