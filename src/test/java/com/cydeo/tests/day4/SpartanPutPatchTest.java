package com.cydeo.tests.day4;



import com.cydeo.utility.SpartanTestBase;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SpartanPutPatchTest extends SpartanTestBase {



    /*
    Faker faker=new Faker();
        String strBody1 = "{\n" +
                "                    \"name\":\""+faker.name().firstName()+"\",\n" +
                "                    \"gender\":\"Male\",\n" +
                "                    \"phone\":"+faker.numerify("##########")+"\n" +
                "          }" ;
     */
    @Test
    public void testPut() {


        String updatedBody = "{\n" +
                "             \"name\":\"New Force\",\n" +
                "             \"gender\":\"Male\",\n" +
                "             \"phone\":5555555555\n" +
                "         }" ;



             given().
                log().all().pathParam("id",2186).contentType(ContentType.JSON)
                .body(updatedBody)
                .when()
                .put("/spartans/{id}")
                .then().
                log().all()
                .statusCode(equalTo(204));



    }

@Tag("tc1")
    @Test
    public void testPatch() {


        String updatedBody = "{\n" +
                "             \"name\":\"Clear Mind \"}" ;



        given().
                log().all().pathParam("id",2186).contentType(ContentType.JSON)
                .body(updatedBody)
                .when()
                .patch("/spartans/{id}")
                .then().
                log().all()
                .statusCode(equalTo(204));



    }

    @Test
    public void testDelete(){


//      Response response= get("/spartans");
//           int lastId=   response.path("id[-1]");

        int lastId= get("/spartans").path("id[-1]");



        given()
                .log().all()
                .pathParam("id", lastId)// dynamic way to delete data , from the last id
                .when()
                .delete("/spartans/{id}")
                .then()
                .log().all()
                .statusCode(is(204));

    }
}
