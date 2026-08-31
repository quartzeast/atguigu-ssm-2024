package com.example.ioc.config;

import com.example.ioc.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@Configuration
public class PersonConfig {
    @Bean
    public Person luna() {
        Person person = new Person();
        person.setName("luna");
        person.setAge(10);
        person.setAddress("somewhere in the world.");
        System.out.println("luna created");
        return person;
    }

    @Bean
    @Scope("prototype")
    public Person anna() {
        Person person = new Person();
        person.setName("anna");
        person.setAge(20);
        person.setAddress("somewhere in the world.");
        System.out.println("anna created");
        return person;
    }

    @Bean
    @Lazy
    public Person jersey() {
        Person person = new Person();
        person.setName("jersey");
        person.setAge(30);
        person.setAddress("somewhere in the world.");
        System.out.println("jersey created");
        return person;
    }
}
