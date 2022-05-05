package com.cydeo.tests.day6;

import com.cydeo.pojo.Driver;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class FormulaOneApiTest {

    /**
     * Here is the import link for whole collecgion
     * https://www.getpostman.com/collections/4ea3cf2262b1b19a6d29
     *
     * It's for historical formula one race information
     * In this particular api , it decided to give you ml by default for response type
     * and In this particular api , it decided to give you json if you add .json at the  end of url
     * for example
     * http://ergast.com/api/f1/drivers --->return xml
     * http://ergast.com/api/f1/drivers.json ---> return json
     *
     * Our objective is to practice json path to find the values in json resul
     * also practice deserialization by converting single driver json to POJO
     * practice converting driver json array in to List</Driver>
     *
     * print out italian drivers
     */



    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://ergast.com" ;
        RestAssured.basePath = "/api/f1" ;

    }

    @AfterAll
    public static void teardown(){
        reset();
    }


    @Test
    public void testDriverDeserialization(){


        JsonPath jp =
                get("/drivers.json")
                       // .prettyPeek()
                        .jsonPath();

        Driver d1 = jp.getObject("MRData.DriverTable.Drivers[0]",Driver.class);
        System.out.println("d1 = " + d1);


        List<Driver> allDrivers = jp.getList("MRData.DriverTable.Drivers",Driver.class);

        System.out.println("allDrivers = " + allDrivers);


        //Find all the drivers who are italian
        for (Driver driver : allDrivers) {
            if(driver.getNationality().equals("Italian")){
                System.out.println(driver.getGivenName()+" "+driver.getFamilyName());
            }
        }


    }







}



