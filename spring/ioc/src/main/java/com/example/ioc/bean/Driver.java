package com.example.ioc.bean;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Data;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
public class Driver implements InitializingBean, DisposableBean {
    private String username;
    private String key;
    private Car car;

    @Autowired
    public void setCar(Car car) {
        this.car = car;
        System.out.println("[Driver] -- setter 自动注入完成: " + car);
    }

    public Driver() {
        System.out.println("[Driver] -- Driver 构造器完成");
    }

    // 这两个注解由 InitDestroyAnnotationBeanPostProcessor 这个 BeanPostProcessor 进行处理
    @PostConstruct
    public void postConstruct() {
        System.out.println("[Driver] -- [postProcessBeforeInitialization] -- @PostConstruct 完成");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("[Driver] -- [postProcessAfterInitialization] -- @PreDestory 完成");
    }

    public void initDriver() {
        System.out.println("[Driver] -- @Bean 指定 initMethod 完成");
    }

    public void destroyDriver() {
        System.out.println("[Driver] -- @Bean 指定 destroyMethod 完成");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("[Driver] -- [InitializingBean] -- afterPropertiesSet....");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("[Driver] -- [DisposableBean] -- destroy.....");
    }
}