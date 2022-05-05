package com.cydeo.runner;


import org.junit.platform.suite.api.*;

@Suite
@SuiteDisplayName("Smoke Test")
//@SelectPackages("com.cydeo.tests.day9")//to run day8 package
//@SelectPackages({"com.cydeo.tests.day8","com.cydeo.tests.day1"}) //to run more than one package
//@SelectClasses(BaseAuthTest.class) //run one class

@SelectPackages("com.cydeo.tests") //start looking from this location
//@IncludeTags({"smoke1","smoke2"}) // run anything with tag 'smoke1'
//@IncludeTags({"smoke2","tc1"})
//@ExcludeTags("smoke3")
@IncludeTags("db")

public class TestRunner {



    // MAKE SURE THE TEST CLASSES YOU SELECT HAVE FOLLOWED NAMING CONVENTION
// ClassName should be SomethingTest
// MethodName should be testSomething




    /*
    ## API - DB Validation
Just like we did on UI - DB validation for example the dashboard number in library app :
 we got expected result from database and compare that with the UI Dashboard number,

We can do similar type of test for API-DB validation.
The objective is the response that coming from the api request is matching actual data in the database.

Sometime , when the api is created , it is not actually connected to the real database to
 prototype the response with some dummy data so UI can display those data by calling the api that return dummy data.
When it's actually connected to the database, we can make sure the data is actually coming
from database rather than dummy data as response.
     */

}
