package com.example.ioc.config;

import com.example.ioc.bean.Dog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 配置类是一种特殊组件，作用是集中归类管理其他普通组件。使用 @Configuration 进行标注。
// Spring 启动时会自动加载配置类，配置类本身也是容器中的组件，必须标注 @Configuration 才能被 SpringBoot 扫描并注册到容器中进行管理
@Configuration
public class DogConfig {
    @Bean
    public Dog dog01() {
        Dog dog = new Dog();
        dog.setName("大狗嚼");
        return dog;
    }
}
