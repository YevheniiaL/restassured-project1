package com.cydeo.tests.officeHours.day2;

import com.cydeo.utility.HrORDSTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class P3_HamCrest extends HrORDSTestBase {


/*
      Given
               accept type is application/json
       When
               user sends get request to /regions
       Then
               response status code must be 200
               verify Date has values
               first region name is Europe
               Regions name should be same order as "Europe","Americas","Asia","Middle East and Africa"
               region ids needs to be 1,2,3,4

    */


    @Test
    public void testGetRegions() {

// DISADVANTAGE OF HAMCREST MATCHERS THAT IT IS NOT REUSABLE, JUST FOR VERIFICATION

        Response response = given().accept(ContentType.JSON)
                .log().uri()
                .when()
                .get("/regions")
                .then().log().all()
                .statusCode(200)
                .header("Date", notNullValue())
                .body("items[0].region_name", is("Europe"))
                .body("items[0].region_id", equalTo(1))
                .body("items.region_name", containsInRelativeOrder("Europe", "Americas", "Asia", "Middle East and Africa"))
                .body("items.region_id", containsInRelativeOrder(1, 2, 3, 4)).extract().response();





        JsonPath jsonPath = response.jsonPath();

        List<String> list = jsonPath.getList("items.region_name");
        System.out.println("list = " + list);

        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));


    }


}
