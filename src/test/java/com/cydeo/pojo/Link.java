package com.cydeo.pojo;


import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor

@ToString
public class Link {

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    private String rel;
    private String href;
}