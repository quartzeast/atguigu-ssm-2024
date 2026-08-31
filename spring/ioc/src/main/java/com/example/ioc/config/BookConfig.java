package com.example.ioc.config;

import com.example.ioc.bean.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class BookConfig {

    // @Primary：同类型 Bean 有多个时，作为默认注入对象
    @Bean
    @Primary
    public Book springBook() {
        return new Book("Spring 实战", "Craig Walls");
    }

    @Bean
    public Book javaBook() {
        return new Book("Java 编程思想", "Bruce Eckel");
    }

    @Bean
    public Book mysqlBook() {
        return new Book("高性能 MySQL", "Baron Schwartz");
    }

    @Bean
    public Book goBook() {
        return new Book("Go 语言编程", "Donovan & Kernighan");
    }
}
