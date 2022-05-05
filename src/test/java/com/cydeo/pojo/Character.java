package com.cydeo.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * {
 * "char_id": 1,
 * "name": "Walter White",
 * "occupation": [
 * "High School Chemistry Teacher",
 * "Meth King Pin"
 * ],
 * "status": "Presumed dead",
 * "nickname": "Heisenberg",
 * "appearance": [
 * 1,
 * 2,
 * 3,
 * 4,
 * 5
 * ],
 * }
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Character {

    @JsonProperty("char_id") //telling json to match name from json to java
    private int id;
    private String name;
    private String [] occupation; // we choose the data type (arr or list) to store it
    private String status;
    private String nickname;
    private List<Integer> appearance;


}
