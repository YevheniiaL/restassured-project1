package com.cydeo.tests.day6;

import com.cydeo.pojo.Car;
import org.junit.jupiter.api.Test;

public class TestCar {



    @Test
    public void testPOJO_CarClass(){

        Car car1 = new Car("Model13","Tesla",2020,true);

        System.out.println("car1 = " + car1);
        car1.setModel("CyberTruck");

        System.out.println("car1.getModel() = " + car1.getModel());


    }
}
