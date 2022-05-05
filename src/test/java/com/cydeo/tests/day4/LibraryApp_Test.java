package com.cydeo.tests.day4;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.imageio.stream.ImageInputStream;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class LibraryApp_Test {


    /**
     * This is the link for doc : https://library2.cybertekschool.com/rest/v1/#/User/post_login
     * <p>
     * POST https://library2.cybertekschool.com/rest/v1/login
     * content type : application/x-www-form-urlencoded
     * body :
     * email      : librarian52@library
     * password   : Sdet2022*
     * <p>
     * According to the doc
     * we get 200 status code
     * json body with 2 key  : token  , redirect_url
     * content-type json
     */


    @BeforeAll
    public static void setUp() {

        baseURI = "https://library2.cybertekschool.com";
        basePath = "/rest/v1";

    }

    @AfterAll
    public static void tearDown() {

        reset();

    }


    @Test
    public void testLoginPost() {

        String s = null;
        s.concat("one");


        //in this POST request we saw different content type known as url encoded form data
        //if content type is this, rest assured make it easy to provide the body
        //using the method called form param, if you have more than one you can keep calling the method
        //and provide the key value pair according to the document

        String tokenFromResponse =  given().
               log().uri().
               contentType(ContentType.URLENC).
               formParam("email", "librarian52@library")
               .formParam("password","Sdet2022*")
               .when()
               .post("/login").path("token");
               // .then().statusCode(is(200)).log().all();



        System.out.println("tokenFromResponse = " + tokenFromResponse);

        given().log().all().header("x-library-token",tokenFromResponse)
                .when()
                .get("/dashboard_stats").
                then()
                .log().all()
                .statusCode(is(200))
                .body("book_count",equalTo("7981"))
                .body("borrowed_books",equalTo("4473"))
                .body("users",equalTo("6897"));


        /*
        {
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjp7ImlkIjoiMjgwIiwiZnVsbF9uYW1lIjoiVGVzdCBMaWJyYXJpYW4gNTIiLCJlbWFpbCI6ImxpYnJhcmlhbjUyQGxpYnJhcnkiLCJ1c2VyX2dyb3VwX2lkIjoiMiJ9LCJpYXQiOjE2MzczMzk5MTcsImV4cCI6MTYzOTkzMTkxN30.pFtWUjDC314r33UIi7FDzLnmFKD4ZjpBX1CUAIi4u3Y",
    "redirect_uri": "//library2.cybertekschool.com/redirect.html?t=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjp7ImlkIjoiMjgwIiwiZnVsbF9uYW1lIjoiVGVzdCBMaWJyYXJpYW4gNTIiLCJlbWFpbCI6ImxpYnJhcmlhbjUyQGxpYnJhcnkiLCJ1c2VyX2dyb3VwX2lkIjoiMiJ9LCJpYXQiOjE2MzczMzk5MTcsImV4cCI6MTYzOTkzMTkxN30.pFtWUjDC314r33UIi7FDzLnmFKD4ZjpBX1CUAIi4u3Y"
}
         */




    }


    /*
    // In separate test , make a request to POST /login one more time
    // no need for assertion , only save the json value of token key in the response
    // send a request to GET /dashboard_stat
    // provide a header with name x-library-token , value is the value you saved from previous request
    // verify you get 200.
     */

}











