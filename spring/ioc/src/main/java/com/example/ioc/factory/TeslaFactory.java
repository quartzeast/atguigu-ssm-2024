package com.example.ioc.factory;

import com.example.ioc.bean.Car;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class TeslaFactory implements FactoryBean<Car> {

    @Override
    public Car getObject() {
        Car car = new Car();
        car.setModel("model3");
        car.setColor("white");
        return car;
    }

    @Override
    public Class<?> getObjectType() {
        return Car.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
