package com.cydeo.tests.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class TestOneSpartan2 {


   // get("http://107.21.187.92:8000/api/spartans/1")


    @BeforeAll
    public static void setup(){

        RestAssured.baseURI = "http://54.236.150.168:8000" ;
        RestAssured.basePath = "/api";

    }

    @AfterAll
    public static void teardown(){
        // in order to avoid the static value accidentally carried over
        // to different class when we practice different api ,
        // it's better if we set baseURI basePath back to it's original value using reset method
        //RestAssured.reset();
        reset();  //

    }


    @Test
    public void testHello(){
       Response response =  get("/hello");
      assertEquals(200,response.statusCode());
      assertEquals("text/plain;charset=UTF-8",response.getContentType());
      assertEquals("Hello from Sparta",response.asString());
    }


    @Test
    public void testOneSpartan(){

      Response response =   get("/spartans/10");

      int statusCode = response.statusCode();
        System.out.println("statusCode = " + statusCode);

        //prettyPrint() and asString() -->same methods (except first one is printing , second one must be printed
        response.prettyPrint();
        System.out.println("response.asString() = " + response.asString());


        //getting header from the response
        //header() and getHeader() -->the same methods

        System.out.println("response.header(\"Content-Type\") = " + response.header("Content-Type"));
        System.out.println("response.getHeader(\"Content-Type\") = " + response.getHeader("Content-Type"));

        System.out.println("response.getHeader(\"Date\") = " + response.getHeader("Date"));
        System.out.println("response.header(\"Date\") = " + response.header("Date"));

        System.out.println("response.header(\"Keep-Alive\") = " + response.header("Keep-Alive"));
        System.out.println("response.header(\"Keep-Alive\") = " + response.header("Keep-Alive"));
    }



    @Test
    public void testContentTypeHeader(){

        Response response =   get("/spartans/10");

        System.out.println("response.contentType() = " + response.contentType());
        System.out.println("response.getContentType() = " + response.getContentType());

        assertEquals("application/json",response.getContentType());
        assertEquals("application/json",response.getHeader("Content-Type"));

        //RestAssure type of content type is represented in ENUM coming from
        //import io.restassured.http.ContentType
        System.out.println("ContentType.JSON = " + ContentType.JSON); //returns ENUM obj
        System.out.println("ContentType.XML = " + ContentType.XML);
        System.out.println("ContentType.TEXT = " + ContentType.TEXT);
        System.out.println("ContentType.URLENC = " + ContentType.URLENC);

        Assertions.assertEquals(ContentType.JSON.toString(),response.getContentType());


    }



    @Test
    public void testJSONBody(){

        /**
         * {
         *     "id": 1,
         *     "name": "Kimberly",
         *     "gender": "Male",
         *     "phone": 3584128232
         * }
         */
        Response response =   get("/spartans/1");
        response.prettyPrint();

        //just like xpath helps us to navigate through HTML, we can use jsonpath to navigate through json
        //the easiest way to get json path is using path method from response

        //provide the key , to get the value
        System.out.println("response.path(\"name\") = " + response.path("name"));
        System.out.println("response.path(\"id\") = " + response.path("id"));
        System.out.println("response.path(\"gender\") = " + response.path("gender"));
        System.out.println("response.path(\"phone\") = " + response.path("phone"));

        int myId = response.path("id");
        String myName = response.path("name");
        String myGender = response.path("gender");
        long myPhoneNumber = response.path("phone");

        System.out.println("myId = " + myId);
        System.out.println("myName = " + myName);
        System.out.println("myGender = " + myGender);
        System.out.println("myPhoneNumber = " + myPhoneNumber);

        Assertions.assertEquals(1,myId);
        Assertions.assertEquals("Kimberly",myName);
        Assertions.assertEquals("Male",myGender);
        Assertions.assertEquals(3584128232L,myPhoneNumber);

    }

}
