package com.cydeo.tests.day6;

import com.cydeo.pojo.Articles;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class NewsAPI_DeserializationTest {
    /**
     * GET https://newsapi.org/v2/top-headlines?country=us
     * Header :  Authorization = Bearer cbeb9b369672417ba25af35e95edbb69
     */


    @BeforeAll
    public static void setUp(){
        baseURI="https://newsapi.org";
        basePath="/v2";
    }
    @AfterAll
    public static void teardown(){
        reset();
    }


@Test
    public void testNews() {

    JsonPath jp = given().queryParam("country", "us")
            .header("Authorization", "Bearer cbeb9b369672417ba25af35e95edbb69")
            .when().get("/top-headlines")
            // .prettyPeek()
            .jsonPath();


    Articles a1 = jp.getObject("articles[0]", Articles.class);
    System.out.println("a1 = " + a1);


    System.out.println("a1.getSource().get(\"id\") = " + a1.getSource().get("id"));

    List<Articles> allArticles = jp.getList("articles", Articles.class);




//Interview task:

//step 1: Create a POJO to represent article :
//fields:source, author, title
//get a List<Article> from json array
//print out the name of author and article name if source id is not null

//solution 1:
    allArticles.stream().filter(p -> p.getSource().get("id") != null).forEach(p -> System.out.println(p.getAuthor()));

//solution 2:
    for (Articles each : allArticles) {
        // check if the source id is null
        if (each.getSource().get("id") != null) {

            System.out.println(each.getAuthor());

        }


    }


}}
