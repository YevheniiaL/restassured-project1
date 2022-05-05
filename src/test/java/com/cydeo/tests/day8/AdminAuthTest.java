package com.cydeo.tests.day8;

import com.cydeo.utility.SpartanAuthTestBase;
import com.cydeo.utility.SpartanUtil;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class AdminAuthTest extends SpartanAuthTestBase {


    /**
     * As homework
     * Create 3 separate class AdminAuthTest , EditorAuthTest , UserAuthTest
     * Write sperate tests for below requirement to do Role Base access control test
     *      * admin :  create , read , update , delete
     *      * editor:  create , read , update
     *      * user  :  read
     */


    @DisplayName("Admin can create a new Spartan")
    @Test
    public void testAdminPotSpartan(){


        given().auth().basic("admin","admin").log().uri()
                .contentType(ContentType.JSON)
                .body(SpartanUtil.getRandomSpartanPOJO())
                .when()
                .post("/spartans")
                .then()
                .statusCode(is(201));


    }






}
