package com.example.ioc.config;

import com.example.ioc.bean.Category;
import com.example.ioc.bean.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {

    @Bean
    public Product product() {
        return new Product("Java 入门教程", 39.90);
    }

    @Bean
    public Category category() {
        return new Category("编程书籍");
    }
}
