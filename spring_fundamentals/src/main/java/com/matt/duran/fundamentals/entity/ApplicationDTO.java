package com.matt.duran.fundamentals.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApplicationDTO {

    private long id;
    private String name;
    private String description;
    private String owner;

    // lombok provides all of the ctors
    // so that I can do the builder().setter().build() design pattern
}
