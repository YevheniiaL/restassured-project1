package com.cydeo.pojo;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Car {

    private String model;
    private String make;
    private int year;
    private boolean autopilot;

}
