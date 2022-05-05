package com.cydeo.tests.day6;

import com.cydeo.pojo.Job;
import com.cydeo.pojo.Job2;
import com.cydeo.utility.HrORDSTestBase;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class HR_ORDS_Test extends HrORDSTestBase {


    //GET/jobs
    @Test
    public void testJobs(){

        JsonPath jp = given().log().uri().
                when()
                        .get("/jobs")
                       // .prettyPeek()
                        .jsonPath();


        //we want to deserialize first json obj from json arr
        //we want to be able to follow java naming convention for naming
        //we want to ignore json field

        Job j1 = jp.getObject("items[0]",Job.class);
        System.out.println("j1 = " + j1);

        List<Job> list = jp.getList("items", Job.class);
        System.out.println("list = " + list);


        //trying our lambok plugin created class Job2
        Job2 j2 = jp.getObject("items[0]",Job2.class) ;
        System.out.println("j2 = " + j2);
        List<Job2> list1 = jp.getList("items", Job2.class);
        System.out.println("list = " + list1);


    }













}
