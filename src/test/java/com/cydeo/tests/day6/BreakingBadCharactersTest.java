package com.cydeo.tests.day6;
import com.cydeo.pojo.Character;
import io.restassured.RestAssured;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.reset;
@Tag("smokeAll")
public class BreakingBadCharactersTest {


    @BeforeAll
    public static void setup() {

        RestAssured.baseURI = "https://www.breakingbadapi.com/";
        RestAssured.basePath = "/api";

    }

    @AfterAll
    public static void teardown() {
        reset();

    }


    @Test
    public void testCharacters(){

        JsonPath jp = get("/characters").prettyPeek().jsonPath();

        //get first item
        Character ch1 = jp.getObject("[0]", Character.class);
        System.out.println("ch1 = " + ch1);


    }


    // Java Practice HOMEWORK :
    // find out the character names appearance count is exactly 1
    //find DEA agent , print the name

    // ORDS API . AS A HOMEWORK :
    // find out all Jobs name with min_salary more than 5000

}
