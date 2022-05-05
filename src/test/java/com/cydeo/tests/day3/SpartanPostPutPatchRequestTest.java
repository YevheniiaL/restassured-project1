package com.cydeo.tests.day3;

import com.cydeo.utility.SpartanTestBase;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SpartanPostPutPatchRequestTest extends SpartanTestBase {

    @Test
    public void testAdd1DataStringBody(){

        /**
         POST /spartans
         content type is json
         body is
         {
         "name":"API POST",
         "gender":"Male",
         "phone":1231231231
         }
         *
         * expect status 201
         * body json format
         * response body
         * {
         *     "success": "A Spartan is Born!",
         *     "data": {
         *         "id": 544,
         *         "name": "API POST",
         *         "gender": "Male",
         *         "phone": 1231231231
         *     }
         * }
         */



       String strBody = "{\n" +
               "                  \"name\": \"Beauty Around\",\n" +
               "                  \"gender\": \"Female\",\n" +
               "                 \"phone\": 1231231231\n" +
               "              }";



                given().
                        log().all().contentType(ContentType.JSON)
                        .body(strBody)
                                .when()
                        .post("/spartans")
                                .then()
                        .log().all()
                        .statusCode(201)
                        .contentType(ContentType.JSON)
                       .body("success", equalTo("A Spartan is Born!"))
                        .body("data.name", equalTo("Beauty Around"))
                        .body("data.phone.toLong()", is(1231231231L))
                        //toLong() groovy method that converts phone number to long
                        .body("data.gender", equalTo("Female"))

                ;

    }

    @Test
    public void testFullUpdate(){

        String strBody = "{\n" +
                "                  \"name\": \"Sun Shine\",\n" +
                "                  \"gender\": \"Female\",\n" +
                "                 \"phone\": 56473876453\n" +
                "              }";


        given().pathParam("id",56).
                contentType(ContentType.JSON).body(strBody).log().all()
                .when().put("/spartans/{id}").
                then().
                statusCode(is(204))
                .log().all();

    }


    @Test
    public void testPartialUpdate(){

        String partToUpdate = "{\n" +
                "                  \"name\": \"Blue Ray\"}";


        given().pathParam("id", 83).contentType(ContentType.JSON)
                .body(partToUpdate)
                .when()
                .patch("/spartans/{id}")
                .then()
                .statusCode(is(204));


    }

    @Test
    public void testDeleteAndVerifyItIsDeleted(){

        given().pathParam("id",9)
                .when()
                .delete("/spartans/{id}")
                .then()
                .statusCode(is(204));

        given().pathParam("id",9)
                .when()
                .get("/spartans/{id}")
                .then()
                .statusCode(is(404));
    }





}
