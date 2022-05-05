package com.cydeo.tests.day5;

import com.cydeo.pojo.SpartanWithID;
import com.cydeo.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class RestAssuredJsonPthMethodsTest extends SpartanTestBase {

    /**
     * There are two ways to get the response and extract json data
     *
     * path("your jsonpath goes here") return type is T(generic)
     * and decided by your variable data type you store
     *   int myId = response.path("id")
     *
     *  There is a type(class) in RestAssured : JsonPath
     *  that have lots of methods to extract json body from the response
     *  like getInt getString getDouble getObject getList and so on.....
     *  In order to get JsonPath object out of response ,
     *  we call a method called jsonPath() from the response
     *  for example :
     *  JsonPath jp =  response.jsonPath("your acual path goes here")  ;
     *  jp.getInt()  jp.getLong() and so on
     *
     *  The meaning of the word json path when we use in differnet places
     *  json path : -->> when inside the " " means the actual path to get the value (like xpath)
     *  JsonPath  : -->> the RestAssured class that have lots of methods
     *  jsonPath() : -->> the method of Response object to obtain JsonPath object out of response
     *
     */










    @Test
    public void testOneSpartan(){

        int firstId = get("/spartans").path("id[0]");

        System.out.println("firstId = " + firstId);


       Response response =  given().
                log().uri().pathParam("id",firstId)
        .when()
                .get("/spartans/{id}")
               .prettyPeek();


       //save id from response
       //int  myId = response.path("id");


        //Get JsonPath out of response obj
        JsonPath jp = response.jsonPath();
        int  myId = jp.getInt("id");
        String myName  = jp.getString("name");
        long myPhone = jp.getLong("phone");

        System.out.println("myId = " + myId);
        System.out.println("myName = " + myName);
        System.out.println("myPhone = " + myPhone);

        //store json result into map obj
      Map<String, Object>  responseBodyMap = jp.getMap("");
        System.out.println("responceBodyMap = " + responseBodyMap);//the path to get entire body is ""(Empty String)

        // store this json result into a Map object ^
        // the path to get the entire body is empty string because
        // the entire response is json object already!
        // no need for a path to navigate to this json
        // so this method will create a map object
        // and add all the key of json as key and all value of json as value
        // the return that map object


        System.out.println("responseBodyMap.get(\"phone\") = " + responseBodyMap.get("phone"));
    }

    @Test
    public void testSearchExtractData(){


        /*
         // Send Request GET /spartans/search?nameContains=Ea&gender=Male
     // get JsonPath object out of response so you can use specialized methods
     // get totalElement field value using getX method
     // get 3rd element phone using getX method
     // get last element name using getX method
     // save first json in json array into Map using getX method
     // remember getX("your path to the element goes here just like xpath")
         */
        Response response = given().
                     log().uri()
                    .queryParam("nameContains","Ea")
                    .queryParam("gender","Male").
                            when()
                      .get("/spartans/search");
                    //.prettyPeek();

        JsonPath jp = response.jsonPath();

       int  totalElement =  jp.getInt("totalElement");
        System.out.println("totalElement = " + totalElement);

        long  thirdPhone = jp.getLong("content[2].phone");
        System.out.println("thirdPhone = " + thirdPhone);

        String lastName = jp.getString("content[-1].name");
        System.out.println("lastName = " + lastName);

        Map<String , Object> mapFirstJson = jp.getMap("content[0]");
        System.out.println("mapFirstJson = " + mapFirstJson);

        List<String > listOfNames = jp.getList("content.name");
        System.out.println("listOfNames = " + listOfNames);

        List<String> allNames = jp.getList("content.name", String.class);//we can specify dataType of List
        System.out.println("allNames = " + allNames);

        List<Long> allPhones = jp.getList("content.phone", Long.class);
        System.out.println("allPhones = " + allPhones);

        List<String> allGenders = jp.getList("content.gender", String.class);
        System.out.println("allGenders = " + allGenders);


        SpartanWithID sp1 = jp.getObject("content[0]",SpartanWithID.class);
        System.out.println("sp1 = " + sp1);

        List<SpartanWithID> spartansWithId = jp.getList("content", SpartanWithID.class);
        System.out.println("spartansWithId = " + spartansWithId);

        spartansWithId.forEach(System.out::println);

    }






}
