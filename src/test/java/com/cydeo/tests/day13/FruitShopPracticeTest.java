package com.cydeo.tests.day13;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;

import java.util.Locale;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FruitShopPracticeTest {


    /**
     * FruitShop Exercise :
     * <p>
     * 1. Get All customers
     * - verify you get 200
     * - then extract first customer id
     * <p>
     * 2. Get All the orders of that customer
     * - verify status 200
     * - then extract first order id
     */
    @BeforeAll
    public static void setup() {
        baseURI = "https://api.predic8.de";
        basePath = "/shop";

    }

    @AfterAll
    public static void teardown() {
        reset();
    }

    public static String id;

    @Test
    @Order(1)
    public void testExtractPractice() {


//send GET request and verify 200 and get id

        //one way:
//        Response response = get("/customers/") ;
//        Assertions.assertEquals(200,  response.statusCode() );
//        Object firstCustomerUrl = response.path("customers.customer_url[0]");
//        System.out.println("firstCustomerUrl = " + firstCustomerUrl);


        // Another way:
        String firstUrl = given().log().uri()
                .when()
                .get("/customers/").
                then().
                statusCode(200)
                .extract().path("customers.customer_url[0]");

        System.out.println("firstUrl = " + firstUrl);

        id = firstUrl.substring(firstUrl.lastIndexOf("/") + 1);
        System.out.println("id = " + id);


    }

    @Test
    @Order(2)
    public void getALlOrdersOfCustomer() {
        given().
                pathParams("customerId", id).
                log().uri().contentType(ContentType.JSON)
                .when().get("/customers/{customerId}/orders/").
                then()
                .statusCode(200).extract()
                .response()
                .prettyPeek();


    }


    @Test
    public void testBreakingTheMethodChain(){

        given()
                .log().uri()
                .contentType(ContentType.JSON).
                when()
                .get("/customers/").
                then()
                .statusCode(200) ;
//**********************************************************************

        // if we break the chain and just store the given part of the chain in the varibale
        // it will be RequestSpecification object because everything inside given section
        // return RequestSpecification
        RequestSpecification givenPart = given().log().uri().contentType(ContentType.JSON);

        // now lets try to break up the when section followed by givenPart
        Response whenPart =  givenPart.when().get("/customers/") ;

        //now let's try to get then() part of the chain
        ValidatableResponse thenPart = whenPart.then().statusCode(200);
        //now let's specify ContentType
        thenPart.contentType(ContentType.JSON);

    }

}


