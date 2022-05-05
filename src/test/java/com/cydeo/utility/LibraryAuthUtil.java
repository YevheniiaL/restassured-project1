package com.cydeo.utility;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class LibraryAuthUtil {



    public static String getToken(String email, String password){

        String libraryToken =  given().
                log().uri().
                contentType(ContentType.URLENC).
                formParam("email", email)
                .formParam("password", password)
                .when()
                .post("/login").path("token");

      return libraryToken;


    }

    public static Map<String,Object> getRandomBookMapBody(){
        Faker faker = new Faker();

        Map<String,Object> bookMap = new LinkedHashMap<>();
        bookMap.put("name",faker.book().title());
        bookMap.put("isbn",faker.code().isbn10());
        bookMap.put("year",faker.numerify("19##"));
        bookMap.put("author",faker.book().author());
        bookMap.put("book_category_id",faker.number().numberBetween(1,20));
        bookMap.put("description",faker.chuckNorris().fact());

        return bookMap;

    }




}
