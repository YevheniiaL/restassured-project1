package com.cydeo.tests.day3;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class HamcrestMatcherExampleTest {

    /**
     * Hamcrest is a framework for writing matcher objects allowing ‘match’ rules
     * to be defined declaratively.
     * It is an assertion library that can be used additionally to make assertion
     * readable and it comes with a lot of pre-written matchers to make it easier to write and read
     *
     * The method chain of RestAssured then section use the hamcrest matcher library
     * extensively to assert the response components in one chain
     * RestAssured dependency already contains Hamcrest Assertion library
     * so no separate dependency needed
     * All we need is to add static imports and start using it's matchers
     * to make the assertions great again
     */

    @Test
    public void testNumbers(){

        //Junit 5: (static import)
        assertEquals(9,3+6);

        //Hamcrest: (static import)

        assertThat(3+6,equalTo(9)); //ONE WAY

        assertThat(4+6,is(10));//SECOND WAY

        assertThat(5+6,greaterThan(3));

        assertThat(10+10, lessThanOrEqualTo(20));
      /* assertThat(5+6,greaterThan(30));

      Expected: a value greater than <30>
        but: <11> was less than <30>
       */


    }


    @Test
    public void testString(){


        String msg = "B23 is Excellent!";

        assertThat(msg,is("B23 is Excellent!"));
        assertThat(msg,equalTo("B23 is Excellent!"));
        assertThat(msg, startsWith("B23"));
        assertThat(msg, startsWithIgnoringCase("b23"));
        assertThat(msg, containsString(" "));

        assertThat(msg, not("Hello"));
    }


    @Test
    public void testCollections(){


        List<Integer> nums = Arrays.asList(3,5,1,77,44,76);

        // check this list has size of 6
        assertThat(nums.size(),is(6));
        assertThat(nums.size(),equalTo(6));
        assertThat(nums,hasSize(6));


        // check this list has item 77
        assertThat(nums,hasItem(77));

        // check this list has items 5, 44
        assertThat(nums,hasItems(5,44));

        // check if this list has items  greatThan 50
        assertThat(nums, hasItems(greaterThan(50)));

        // check this list every item is greatThan 0
        assertThat(nums,everyItem(greaterThan(0)));



    }






}
