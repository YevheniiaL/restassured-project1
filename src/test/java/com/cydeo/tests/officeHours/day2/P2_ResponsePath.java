package com.cydeo.tests.officeHours.day2;

import com.cydeo.utility.HrORDSTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P2_ResponsePath extends HrORDSTestBase {



    /*
       Given
                accept type is application/json
        When
                user sends get request to /regions/2
        Then
                response status code must be 200
                region_name is Americas
                region_id is 2
                print out all the links

     */




   @ParameterizedTest
    @CsvFileSource(resources ={"/regions.csv"},numLinesToSkip = 1)
    public void getRegionParametrized(Integer id,String regionName ) {


        Response response = given().contentType(ContentType.JSON)
                .pathParam("id", id)
                .when()
                .get("/regions/{id}").prettyPeek();


        assertEquals(200,response.statusCode());

       Integer region_id = response.path("region_id");
        System.out.println("region_id = " + region_id);

        String region_name = response.path("region_name");
        System.out.println("region_name = " + region_name);

        List<Map<String,String> > allLinks= response.path("links");

       // System.out.println("allLinks = " + allLinks);
        for (Map<String, String> link : allLinks) {


            System.out.println("allLinks.get(\"rel\") = " + link.get("rel"));
            System.out.println("allLinks.get(\"href\") = " + link.get("href"));
        }

        assertEquals(regionName,region_name);
        assertEquals(id,region_id);


    }








}
