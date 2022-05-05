package com.cydeo.tests.day12;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class MockAPIEndpointTest {

    @BeforeAll
    public static void setup(){
        RestAssured.baseURI = "https://1308eb65-22a4-4d50-b7f5-b5e44f79b3a2.mock.pstmn.io" ;
        // we did not add anything after baseURI in mock api endpoint so we dont have basepath here
    }


    @Test
    public void testNemo(){

        // get("/nemo").prettyPeek() ;
        given()
                .log().all().
                when()
                .get("/nemo").
                then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("type" , is("lets have lunch people!") )

        ;
    }

    /**
     * When sending larger amount of data to the server it's common to
     *  use the multipart form data technique. Rest Assured provide methods
     *  called multiPart that allows you to specify a file, byte-array, input stream
     *  or text to upload
     */
    @Test
    public void testMultiPartFormDataFileUpload(){

        given()
                .log().all()
                .contentType(ContentType.MULTIPART)
                .multiPart( new File("src/test/java/com/cydeo/tests/day12/Gherkin.jpeg") ).
                when()
                .post("/upload").
                then()
                .statusCode(200) ;



    }

    @AfterAll
    public static void teardown(){
        reset();
    }}
