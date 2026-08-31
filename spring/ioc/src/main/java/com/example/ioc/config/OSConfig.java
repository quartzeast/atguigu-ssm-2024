package com.example.ioc.config;

import com.example.ioc.condition.MacCondition;
import com.example.ioc.condition.WindowsCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OSConfig {
    // 场景：根据当前电脑的操作系统注册对应的 bean
    @Conditional(MacCondition.class)
    @Bean("MacOS")
    public com.example.ioc.bean.OS macOS() {
        com.example.ioc.bean.OS mac = new com.example.ioc.bean.OS();
        mac.setName("MacOS");
        return mac;
    }

    @Conditional(WindowsCondition.class)
    @Bean("Windows")
    public com.example.ioc.bean.OS windows() {
        com.example.ioc.bean.OS windows = new com.example.ioc.bean.OS();
        windows.setName("Windows");
        return windows;
    }
}
