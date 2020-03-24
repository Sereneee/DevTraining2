package com.example.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {
    private String name;
    private int age;

    public Person() {
    }

    public Person(final String name, final int age) {
        this.name = name;
    }
}
