package com.cydeo.tests.day5;

import com.cydeo.pojo.Movie;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class MovieWithPOJO  {


    /*
        Send this request GET http://www.omdbapi.com/?s=The Mandalorian
    create a POJO class for Movie
    match the java filed to json field
    and save the first one as Movie  POJO
    save all of them as list<POJO>
     */

    @Test
    public void getOneMovieWithPOJO() {

        Response response = given()
                .log().all()
                .queryParam("apikey", "e880831b")
                .queryParam("s", "The Mandalorian").
                when()
                .get("http://www.omdbapi.com/")
                .prettyPeek();

        JsonPath jp = response.jsonPath();


        Map<String, Object> firstMovie = jp.getMap("Search[0]");
        System.out.println("firstMovie = " + firstMovie);

        List<Movie > listOfObj = jp.getList("Search");
        System.out.println("List of all movie objects = " + listOfObj);

        List<String> allTitles = jp.getList("Search.Title", String.class);
        System.out.println("allTitles = " + allTitles);

        Movie movie1  = jp.getObject("Search[0]", Movie.class);

        System.out.println("movie1 = " + movie1);
        System.out.println("movie1.getTitle() = " + movie1.getTitle());


    }


}
