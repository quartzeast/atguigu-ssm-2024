package com.example.ioc.bean;

import com.example.ioc.annotation.UUID;
import lombok.Data;

@Data
public class Car {
    @UUID
    private String id;

    String model;
    String color;
}
