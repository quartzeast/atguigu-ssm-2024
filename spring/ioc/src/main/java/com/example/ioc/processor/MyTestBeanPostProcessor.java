package com.example.ioc.processor;

import com.example.ioc.bean.Driver;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component // 拦截所有 Bean 的后置处理器
public class MyTestBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(
            Object bean, String beanName) throws BeansException {

        if (bean instanceof Driver d) {
            System.out.println("[postProcessBeforeInitialization]: " + beanName);
            d.setUsername("rockman test");
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(
            Object bean, String beanName) throws BeansException {

        if (bean instanceof Driver d) {
            System.out.println("[postProcessAfterInitialization]: " + beanName);
        }

        return bean;
    }
}
