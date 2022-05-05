package com.cydeo.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
public class Article2 {


    private Source source; //because we have a POJO for Source that has id and name
    private String author;
    private String title;






}
