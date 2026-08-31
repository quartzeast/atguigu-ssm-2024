package com.example.ioc.bean;

import lombok.Data;

@Data
public class Dog {
    private String name;

    public Dog() {
        System.out.println("Dog constructor...create a dog");
    }
}
