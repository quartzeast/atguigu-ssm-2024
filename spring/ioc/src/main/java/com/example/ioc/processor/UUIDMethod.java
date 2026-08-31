package com.example.ioc.processor;

import com.example.ioc.annotation.UUID;
import lombok.SneakyThrows;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component // 拦截所有 Bean 的后置处理器
public class UUIDMethod implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return fillUuid(bean);
    }

    @SneakyThrows
    private Object fillUuid(Object bean) {
        Field[] fields = bean.getClass().getDeclaredFields();

        for (Field field : fields) {
            // 判断属性是否有 UUID 注解，且属性类型为 String
            if (field.isAnnotationPresent(UUID.class)
                    && field.getType().equals(String.class)) {
                // 设置属性可访问
                field.setAccessible(true);

                // 仅在属性尚未赋值时生成 UUID，避免前后两个阶段重复生成
                if (field.get(bean) == null) {
                    field.set(bean, java.util.UUID.randomUUID().toString());
                }
            }
        }

        return bean;
    }
}
