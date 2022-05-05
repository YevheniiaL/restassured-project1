package com.cydeo.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Map;

//step 1: Create a POJO to represent article :
//fields:source, author, title
//get a List<Article> from json array
//print out the name of author and article name if source id is not null

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Articles {


    private Map<String,Object> source; // when we need
    private String author;
    private String title;






}
