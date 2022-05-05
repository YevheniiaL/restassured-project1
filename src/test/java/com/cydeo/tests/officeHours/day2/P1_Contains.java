package com.cydeo.tests.officeHours.day2;

import com.cydeo.utility.HrORDSTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P1_Contains extends HrORDSTestBase {



    /*
       Given
                accept type is application/json
        When
                user sends get request to /regions/2
        Then
                response status code must be 200
                content type equals to application/json
                response body contains   Americas

     */


    @Test
    @DisplayName("Test with contains")
    public void getRegion() {

        Response response = given().contentType(ContentType.JSON)
                .pathParam("id", 2)
                .when()
                .get("/regions/{id}").prettyPeek();


        assertEquals(200, response.statusCode());


        assertAll(
                () -> assertTrue(response.asString().contains("Americas")),
                () -> assertEquals(ContentType.JSON.toString(), response.contentType()));


    }


}
