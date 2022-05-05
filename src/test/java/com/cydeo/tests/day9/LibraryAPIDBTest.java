package com.cydeo.tests.day9;

import com.cydeo.utility.DB_Util;
import com.cydeo.utility.LibraryAuthUtil;
import com.cydeo.utility.LibraryTestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;

public class LibraryAPIDBTest extends LibraryTestBase {



    @Test
    public void testDB(){
        DB_Util.runQuery("select * from books");
       // DB_Util.displayAllData();
        System.out.println("DB_Util.getRowCount() = " + DB_Util.getRowCount());


    }


    @DisplayName("Test GET /Dashboard response match database")
    @Test
    public void testDashboardStatsMatchDB(){

        DB_Util.runQuery("select count(*) from books");
        String expectedBookCount = DB_Util.getCellValue(1, 1);
        DB_Util.runQuery("select count(*) from users");
        String expectedUsersCount = DB_Util.getCellValue(1, 1);
        DB_Util.runQuery("select count(*) from book_borrow where is_returned=0");
        String expectedBookBorrowCount = DB_Util.getCellValue(1, 1);




        given().log().uri()
                .header("x-library-token",
                        LibraryAuthUtil.getToken("librarian15@library","Sdet2022*"))
                .when()
                .get("/dashboard_stats")
        .then()
                .statusCode((200))
                .body("book_count",equalTo(expectedBookCount))
                .body("borrowed_books",equalTo(expectedBookBorrowCount))
                .body("users",equalTo(expectedUsersCount));





    }















}
