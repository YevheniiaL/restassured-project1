package com.cydeo.tests.day5;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class WeatherTask {

    /*
    Interview question :
Send Request to GET api.openweathermap.org/data/2.5/weather?q=London&appid={API key}
AND print out the max and min temperature
     */


    @Test
    public void testWeather(){
        Response response = given()
                .log().all()
                .queryParam("q","London")
                .queryParam("appid", "1d62ad5caf9c8023a9572b311aaaba69").
                when()
                .get("http://api.openweathermap.org/data/2.5/weather")
                .prettyPeek();

        JsonPath jp = response.jsonPath();

        double minTemp = jp.getDouble("main.temp_min");
        double maxTemp = jp.getDouble("main.temp_max");
        System.out.println("minTemp = " + minTemp);
        System.out.println("maxTemp = " + maxTemp);

        reset();

    }


}
