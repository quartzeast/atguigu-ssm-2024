package com.example.ioc.bean;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class Cat {
    @Value("bibi")
    private String name;

    @Value("${cat.age}")
    private Integer age;

    @Value("${cat.color}")
    private String color;

    @Value("#{10*20}")
    private Integer speed;

    @Value("#{T(java.util.UUID).randomUUID().toString()}")
    private String id;

    @Value("#{'miao..miao...'.substring(0, 5)}")
    private String message;

    @Value("#{new String('Persian').toUpperCase()}")
    private String breed;

    @Value("#{new int[]{1, 3, 5, 7, 9}}")
    private int[] numbers;
}
