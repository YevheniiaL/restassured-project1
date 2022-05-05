package com.cydeo.utility;

import com.cydeo.pojo.Spartan;
import com.github.javafaker.Faker;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class SpartanUtil {


    public static Map<String, Object> GetRandomSpartanMapBody() {
       // Create random data with Faker
       // optionally :create a utility out of it

        Faker faker = new Faker();

        Map<String, Object> bodyMap = new LinkedHashMap<>();
        bodyMap.put("name", faker.name().firstName());
        bodyMap.put("gender", faker.demographic().sex()); //Male or Female
        bodyMap.put("phone", faker.number().numberBetween(5000000000L,9999999999l));  // get number between 5000000000-99999999999

       return bodyMap;
    }

    public static int  getRandomNumber(int num1, int num2){

        Faker faker = new Faker();
        int number  = faker.number().numberBetween(num1,num2);
        return number;

    }

    public static int getRandomId(){

        int randomId =  SpartanUtil.getRandomNumber((get("/spartans").path("id[0]")),
                get("/spartans").path("id[-1]"));
        return randomId;
    }



   public static Spartan getRandomSpartanPOJO(){

        Faker faker = new Faker();

       Spartan spartan = new Spartan(faker.name().lastName(),faker.demographic().sex(),
               faker.number().numberBetween(5000000000L,9999999999l));

       return spartan;



   }
}
