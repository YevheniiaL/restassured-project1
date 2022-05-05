package com.cydeo.tests.practice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class Task1_StarWarsAPI {


    @BeforeAll
    public static void setup() {

        RestAssured.baseURI = "https://swapi.dev";
        RestAssured.basePath = "/api";

    }

    @AfterAll
    public static void teardown() {
        // in order to avoid the static value accidentally carried over
        // to different class when we practice different api ,
        // it's better if we set baseURI basePath back to it's original value using reset method
        //RestAssured.reset();
        reset();  //

    }

    @Test
    public void SwapiTest() {

        Response response = get("/people");
        response.prettyPrint();

        //   - Verify status code is 200
        assertEquals(200, response.statusCode());

        //  - header Content-Type is application/json
        assertEquals(ContentType.JSON.toString(), response.getContentType());

        //   - count field value is 82
        int count = response.path("count");
        assertEquals(82, count);

        // - name of first person in the result is Luke Skywalker
        String name = response.path("results.name[0]");
        assertEquals("Luke Skywalker", name);

        //- hair_color of fourth person in the result is none
        assertEquals("none", response.path("results.hair_color[3]"));

        //- birth_year of last person is 57BBY

        String birthYear = response.path("results[-1].birth_year");// last person on returned page
        assertEquals("57BBY", birthYear);

        //- save the name of all characters in the result into List and verify the count is 10 (10 per page)
        List<String> names = response.path("results.name");
        assertEquals(10, names.size());

        // -  Save the height of all people from the result and find out the max height.
        ArrayList<String> heights = response.path("results.height");
        System.out.println("heights = " + heights);

        ArrayList<Integer> allHeights = new ArrayList<>();


        for (String each : heights) {
            allHeights.add(Integer.valueOf(each));
        }

        int max = allHeights.get(0);
        for (Integer each : allHeights) {
            if (max < each) {
                max = each;
            }

        }

        System.out.println("max = " + max);

    }


    @Test
    public void testPage2() {
    /*
    in swapi api , it return 10 result per page and you can use page query param to
    define which page you want to go for example , second page will be GET https://swapi.dev/api/people?page=2 .
    -   in separate test , use given() section to provide query param page value 2
    */

        Response response = given().queryParam("page", 2).
                when().get("/people");
        response.prettyPrint();

        //-  print the name of 4th person.
        String name = response.path("results[3].name");
        System.out.println("name = " + name);

        //-  verify the 9th person name is Yoda
        String actualName = response.path("results[8].name");
        String expectedName = "Yoda";
        assertEquals(expectedName, actualName);

    }

    @Test
    public void get3Film() {
        /*
        3. Send request to GET https://swapi.dev/api/films/3 (3 in here is path parameter to get single film)
         */

        Response response = given().pathParam("id", 3).when().
                get("films/{id}");

        response.prettyPrint();

        //   - verify you get 200
        assertEquals(200, response.statusCode());

        //   - verify response is json format
        assertEquals(ContentType.JSON.toString(), response.getContentType());

        //- verify the name of film is
        assertEquals("Return of the Jedi", response.path("title"));

        // - save characters field value into List<String> and verify count is 20
        List<String> characters = response.path("characters");

        assertEquals(20,characters.size());


    }


}
