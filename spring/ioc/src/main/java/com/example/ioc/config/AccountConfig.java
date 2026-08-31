package com.example.ioc.config;

import com.example.ioc.bean.Account;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AccountConfig {
    @Bean
    public Account personalAccount() {
        return new Account("A-1001");
    }

    @Bean
    public Account businessAccount() {
        return new Account("A-2001");
    }

    @Bean
    public Account jointAccount() {
        return new Account("A-3001");
    }
}
