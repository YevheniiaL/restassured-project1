package com.cydeo.tests.day8;
import com.cydeo.utility.SpartanAuthTestBase;
import com.cydeo.utility.SpartanUtil;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.reset;
import static org.hamcrest.Matchers.is;

public class BaseAuthTest  extends SpartanAuthTestBase {



    /**
     * Role Based Access Control Test , also known as RBAC
     * is a type of testing to ensure authenticated user can
     * perform everything they have authorized to
     * in the meantime can not perform anything that they are not authorized to perform
     *
     * admin :  create , read , update , delete
     * editor:  create , read , update
     * user  :  read
     */

    /**
     * Given editor credentials are provided
     * When editor try to delete any data
     * Then the response should be 403 forbidden
     */





    /**
     * Create a test method  testPublicUser
     * Send a request GET /spartans without any authentication
     * expect 401
     */

    @Test
    public void testPublicUser(){

        given().log().uri()
                .when()
                .get("/spartans")
                .then().log().all()
                .statusCode(is(401));


    }


    /**
     * Create a test method  testAuthenticatedUser
     * Send a request GET /spartans without any authentication
     * expect 200
     */

    @Test
    public void testAuthenticatedUser(){

        given().log().all()
                .auth().basic("user","user")
                .when()
                .get("/spartans")
                .then()
                .log().ifValidationFails()
                .statusCode(is(200));



    }


    /**
     * Editor is not allowed to delete data --> result 403
     */
    @DisplayName("Editor should not be able to delete")
    @Test
    public void testAuthenticatedEditorDelete(){

        given().log().all()
                .auth().basic("editor","editor")//pathParam("id", 87)->since editor can't even access the data
                              // we don't need the path param (it will return 403->before using pathParam() variable
                .when()
                .delete("/spartans")
                .then()
                .log().ifValidationFails()
                .statusCode(is(403));



    }
    @DisplayName("Authorization with all credentials")
    @ParameterizedTest
    @CsvFileSource(resources = "/spartans_credentials.csv")
    public void testAllUserAuth(String username,String password){


        given().log().uri()
                .auth().basic(username,password)
                .when()
                .get("/spartans")
                .then()
                .statusCode(is(200));
    }






    /**
     *
     * Write a parameterized Test for getting all data
     * with different user credentials
     * use @ValueSource in this particular example
     * since username and passwords are the same , we can just provide
     */

    @DisplayName("Authorization with all credentials 2")
    @ParameterizedTest
    @ValueSource(strings = {"admin","editor","user"})
    public void testAllUserAuth1(String role){


        given().log().uri()
                .auth().basic(role,role)
                .when()
                .get("/spartans")
                .then()
                .statusCode(is(200));
    }



    //HOMEWORK:
    /**
     * As homework
     * Create 3 separate class AdminAuthTest , EditorAuthTest , UserAuthTest
     * Write sperate tests for below requirement to do Role Base access control test
     *      * admin :  create , read , update , delete
     *      * editor:  create , read , update
     *      * user  :  read
     */
}
