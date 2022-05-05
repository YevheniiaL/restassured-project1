package com.cydeo.tests.practice;

import com.cydeo.utility.LibraryAuthUtil;
import com.cydeo.utility.LibraryTestBase;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class LibraryLoginTest extends LibraryTestBase {


    @DisplayName("Login to LibraryCT with valid credentials")
    @ParameterizedTest
    @CsvFileSource(resources = "/library_credentials.csv", numLinesToSkip = 1)
    public void testLoginFunction(String username, String password) {

        System.out.println("username = " + username);
        System.out.println("password = " + password);

        given().
                log().uri().
                contentType(ContentType.URLENC).
                formParam("email", username)
                .formParam("password", password)
                .when()
                .post("/login")
                .then().statusCode(is(200));


    }


    @DisplayName("Librarian should be able to add book")
    @Test
    public void testAddBook(){


        //First get library token by sending POST /login request
        //and save it (eventually make a method out of it)


        //If you have many form parameters as body
        //you can use formParams method and pass map object instead
        //send request to POST /add_book and verify the body
        //contains : "message" : "The book has been created."


        // get and save token , to use it for request authorization

       String libraryToken = LibraryAuthUtil.getToken("librarian52@library","Sdet2022*");


        //using token and body to post a book

        given()
                .log().all()
                .header("x-library-token",libraryToken)
                .contentType(ContentType.URLENC)
                .formParams(LibraryAuthUtil.getRandomBookMapBody())
                .when()
                .post("add_book")
                .then()
                .log().body()
                .body("message", is ("The book has been created."));


    }


}
