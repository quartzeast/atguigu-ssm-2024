package com.example.ioc.config;

import ch.qos.logback.core.CoreConstants;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({CoreConstants.class})
@Configuration
@ComponentScan("com.example") // 组件批量扫描，只扫描使用 Spring 相关注解标注的类注册到容器中
public class AppConfig {
}
