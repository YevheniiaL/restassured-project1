package com.cydeo.tests.day6;

import com.cydeo.pojo.Article2;
import com.cydeo.pojo.Articles;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class NewsAPI_DeserializationCoolTest {
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
            .prettyPeek()
            .jsonPath();


    Article2 a1 = jp.getObject("articles[0]", Article2.class);
    System.out.println("a1 = " + a1);

    List<Article2> articles = jp.getList("articles", Article2.class);


    //Interview task:

    //step 1: Create a POJO to represent article :
//fields:source, author, title
//get a List<Article> from json array
//print out the name of author and article name if source id is not null

//solution 1:
    for (Article2 each : articles) {
      if(  each.getSource().getId()!=null){
          System.out.println( each.getAuthor());

      }

    }

    //solution 2:
    articles.stream().filter(p -> p.getSource().getId() != null).forEach(p -> System.out.println(p.getAuthor()));







        }


    }


