package com.example.ioc.config;

import com.example.ioc.bean.Device;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeviceConfig {

    @Bean
    public Device device() {
        Device device = new Device();
        device.setName("学习电脑");
        return device;
    }
}
