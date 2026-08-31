package com.example.ioc.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource("classpath:conf/fish.properties")
@Data
@Component
public class Fish {
    @Value("${fish.name:bill}")
    private String name;
    @Value("${fish.age:20}")
    private Integer age;
}
