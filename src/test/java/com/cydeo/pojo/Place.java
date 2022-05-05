package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * with these specific fields :
 * *      - name as String
 * *      - postCode as int
 * *      - latitude as float
 * *      - longitude as float
 */

@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Place {

    @JsonProperty("place name")
    private String name;
    @JsonProperty("post code")
    private int postCode;
    private float latitude;
    private float longitude;


}
