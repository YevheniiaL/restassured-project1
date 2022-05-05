package com.cydeo.tests.day8;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MethodSourceTest {

    /**
     * Another way of providing source for @ParametrizedTest is @MethodSource
     * It will allow you to provide the returned value from the static method with no param
     * as the source for your parametrized tes
     */


    @ParameterizedTest
    @MethodSource("get10Number")
    public void testWithMethodSourceDDT(int num) {

        System.out.println("num = " + num);

    }


    //method is in diff class : we must provide full.package.path#staticMethodName
    @ParameterizedTest
    @MethodSource("com.cydeo.utility.MethodSourceUtil#getSomeNames")
    public void testNamesWithMethodSourceDDT(String name){

        System.out.println("name = " + name);

    }


    public static List<Integer> get10Number() {

        ArrayList<Integer> nums = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        return nums;

    }


}
