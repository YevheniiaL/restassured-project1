package com.cydeo.tests.day3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HR_ORDS_API_Test {


    /*
    1. Open a new TestClass called HR_ORDS_API_Test
2. Create @BeforeAll @AfterAll methods for setting up and tearing down
   1. baseURI  = http://54.236.150.168:1000
   2. basePath = /ords/hr/
3. Create a test called testGetAllJobs
   1. Send request to GET /jobs
   2. Save the response
   3. Check status code is 200
   4. Content type is json
   5. count value is 19
   6. save second job_id into String
   7. print 4th mix_salary and
   8. save all of the job_title into List<String>
     */

    @BeforeAll
    public static void setUp() {

        baseURI = "http://54.236.150.168:1000";
        basePath = "/ords/hr/";

    }


    @AfterAll
    public static void tearDown(){
        reset();


    }


    @Test
    public void testGetAllJobs(){

        Response response = given().log().all().when().get("/jobs");

       response.prettyPrint();

        assertEquals(200, response.statusCode());

        assertEquals(ContentType.JSON.toString(), response.contentType());

        int count = response.path("count");
        assertEquals(19,count);

        String jobId = response.path("items[1].job_id");
        System.out.println("jobId = " + jobId);

        int fourthMaxSalary = response.path("items.max_salary[3]");
        System.out.println("fourthMaxSalary = " + fourthMaxSalary);

        int fourthMinSalary = response.path("items.min_salary[3]");
        System.out.println("fourthMinSalary = " + fourthMinSalary);

     List<String> jobTitles =    response.path("items.job_title");
        System.out.println("jobTitles = " + jobTitles);


    }

    /*
    4. Create a test called testJobsWithQueryParam
   1. Send request to `GET /jobs?limit=5`
   2. log everything about the request
   3. verify `count` value is `5`
   4. verify the value of last `job_title` is `AD_VP`
*/


@Test
    public void testJobsWithQueryParam(){

    Response response  = given().queryParam("limit",5).log().all().
            when().
            get("/jobs");

    response.prettyPrint();

    int count = response.path("count");

    assertEquals(5,count);

    // String lastJobId = response.path("items[4].job_id");
    // RestAssured use a language called groovy when working with jsonpath
    // so any valid groovy syntax will work
    // in groovy , collection index can use minus to start from the back
    // so -1 will last item , -2 will be second from the last item

    String lastJobTitle = response.path("items.job_id[-1]");

     assertEquals("AD_VP",lastJobTitle);




}





/*

5. create a test called testSingleJobWithPathParam
   1. Send request to `GET /jobs/AD_VP`
   2. log everything about the request
   3. verify response is json and `job_title` is `Administration Vice President`
     */



    @Test
    public void testSingleJobWithPathParam(){

        Response response = given().pathParam("job_id","AD_VP").
                log().all().
                when().get("/jobs/{job_id}").prettyPeek();


      // response.prettyPrint(); must be called separatelly, because it returns string
        // response.prettyPEEK(); - can be part of our method chaining ,  because it returns Response obj

        assertEquals("Administration Vice President",response.path("job_title"));


    }
}
