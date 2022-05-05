package com.cydeo.tests.day9;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import com.cydeo.utility.DB_Util;
import com.cydeo.utility.HrORDSTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Map;

public class API_DB_Test extends HrORDSTestBase {

    
    static int expectedResult;

    @Test
    public void testRegion() {


        DB_Util.runQuery("select*from regions");
        DB_Util.displayAllData();


    }


    /**
     * Send an API request to GET http://54.236.150.168:1000/ords/hr/regions
     * Get your response
     * Get the expected result count from Database
     * Assert the api request response has same count as database row count
     */


    @DisplayName("Test Region Count from API with DataBase Matchers")
    @Tag("db")
    @Test
    public void testRegionCount() {

        //expected result --> comes from DataBase
        DB_Util.runQuery("select*from regions");
        expectedResult = DB_Util.getRowCount();


        //get actual result
        given().log().uri()
                .when()
                .get("/regions")
                .then()
                .log().ifValidationFails()
                .statusCode(is(200))
                //compare actual vs expected
                .body("count", is(expectedResult))
                .body("items", hasSize(expectedResult));

    }


    @DisplayName("Test Single Region  Data from DataBase with API")
    @Tag("db")
    @Test
    public void testSingleRegion() {

        DB_Util.runQuery("select * from regions where region_id = 1");
        Map<String, String> expectedDBRegionMap = DB_Util.getRowMap(1);

        int expectedRegionId = Integer.parseInt(expectedDBRegionMap.get("REGION_ID"));
        String expectedRegionName = expectedDBRegionMap.get("REGION_NAME");

        given()
                .pathParam("region_id", 1)
                .log().uri()
                .when()
                .get("/regions/{region_id}")
                .then()
                .statusCode(is(200))
                .log().all()
                .body("region_id", equalTo(expectedRegionId))
                .body("region_name", is(expectedRegionName));


    }


    /**
     * HOMEWORK:
     * Write a Parameterized Test to test all regions instead of one above
     * try couple different way
     * 1. @ValueSource  to provide all 4 regions id
     * 2. @MethodSource
     * -- get all id s from api response GET /regions and return List<Integer>
     * 3. @MethodSource
     * -- get all id s from SELECT * FROM REGIONS query and return List<String>
     * and use it as a source
     */


    @DisplayName("Test Each Region  Data from DataBase with API")
    @Tag("db")
    @ParameterizedTest(name = "Region ID : {0}")
    @ValueSource(ints = {1, 2, 3, 4})
    public void testAllRegionsParametrized(int id) {

        DB_Util.runQuery("select * from regions where region_id = " + id);
        Map<String, String> expectedDBRegionMap = DB_Util.getRowMap(1);

        int expectedRegionId = Integer.parseInt(expectedDBRegionMap.get("REGION_ID"));
        String expectedRegionName = expectedDBRegionMap.get("REGION_NAME");

        given()
                .pathParam("region_id", id)
                .log().uri()
                .when()
                .get("/regions/{region_id}")
                .then()
                .statusCode(is(200))
                .log().all()
                .body("region_id", equalTo(expectedRegionId))
                .body("region_name", is(expectedRegionName));


    }


    @DisplayName("Test Each Region Data from API with DataBase")
    @Tag("db")
    @ParameterizedTest(name = "Region id is {0}")
   @MethodSource("getAllId")
    public void testAllRegionsParametrizedWithMethodSource(Integer id) {

        DB_Util.runQuery("select * from regions where region_id = " + id);
        Map<String, String> expectedDBRegionMap = DB_Util.getRowMap(1);

        int expectedRegionId = Integer.parseInt(expectedDBRegionMap.get("REGION_ID"));
        String expectedRegionName = expectedDBRegionMap.get("REGION_NAME");

        given()
                .pathParam("region_id", id)
                .log().uri()
                .when()
                .get("/regions/{region_id}")
                .then()
                .statusCode(is(200))
                .log().all()
                .body("region_id", equalTo(expectedRegionId))
                .body("region_name", is(expectedRegionName));


    }

    public static List<Integer> getAllId(){
        Response response = given()
                .when()
                .get("/regions");

        JsonPath jsonPath = response.jsonPath();
        List<Integer> list = jsonPath.getList("items.region_id", Integer.class);
        return list;

    }





    @DisplayName("Test Each Region  Data from DataBase with API")
    @Tag("db")
    @ParameterizedTest(name = "Region id => {0}")
    @MethodSource("getAllIdFromDB")
    public void testAllRegionsParametrizedWithMethodSourceDB(String id) {

        int region_id = Integer.parseInt(id);

        DB_Util.runQuery("select REGION_NAME from regions where region_id = " + region_id);
        String expectedRegionName = DB_Util.getFirstRowFirstColumn();



        given()
                .pathParam("region_id", region_id)
                .log().uri()
                .when()
                .get("/regions/{region_id}")
                .then()
                .statusCode(is(200))
                .log().all()
                .body("region_id", equalTo(region_id))
                .body("region_name", is(expectedRegionName));


    }

    public   static List<String> getAllIdFromDB(){

        DB_Util.runQuery("select region_id from regions");
        List<String> list = DB_Util.getColumnDataAsList(1);
        return list;


    }




}
