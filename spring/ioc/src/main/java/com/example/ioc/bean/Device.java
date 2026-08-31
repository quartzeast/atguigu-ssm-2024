package com.example.ioc.bean;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

@Data
/*
    Aware 感知接口用于让 Bean 获取 Spring 容器提供的基础信息：
    BeanNameAware 获取当前 Bean 在容器中的名称；
    ApplicationContextAware 获取当前 IOC 容器对象；
    EnvironmentAware 获取当前环境对象，可以读取配置文件和环境变量。
    Spring 会在 Bean 创建完成后自动调用对应的 set 方法。
*/
public class Device implements BeanNameAware, ApplicationContextAware, EnvironmentAware {
    private String name;
    private String beanName;
    private String contextId;
    private String osConfig;

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.contextId = applicationContext.getId();
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.osConfig = environment.getProperty("OSConfig", "未配置");
    }
}
