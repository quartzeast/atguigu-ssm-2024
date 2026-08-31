package com.example.ioc.config;

import com.example.ioc.bean.Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DriverConfig {

    @Bean(initMethod = "initDriver",destroyMethod = "destroyDriver")
    public Driver driver() {
        return new Driver();
    }
}
