package com.cydeo.utility;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public class LibraryTestBase {









    @BeforeAll
    public static void setUp() {

        baseURI = "https://library2.cybertekschool.com";
        basePath = "/rest/v1";

        DB_Util.createConnection(ConfigReader.read("library2.database.url"),
                ConfigReader.read("library2.database.username"),
                ConfigReader.read("library2.database.password"));

    }

    @AfterAll
    public static void tearDown() {

        reset();
        DB_Util.destroy();

    }
}
