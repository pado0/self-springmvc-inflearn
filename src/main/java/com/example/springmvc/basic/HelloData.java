package com.example.springmvc.basic;

import lombok.Data;

@Data // getter, setter, toString, equals, hashcode, requiredConstruct 다 만들어줌
public class HelloData {
    private String username;
    private int age;
}
