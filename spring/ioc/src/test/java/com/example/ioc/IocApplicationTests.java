package com.example.ioc;

import com.example.ioc.dao.DeliveryDao;
import com.example.ioc.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class IocApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    DeliveryDao deliveryDao;

    @Test
    void contextLoads() {
    }

    @Test
    void test01() {
        deliveryDao.saveDelivery();
    }

    @Test
    void test02() {
        String string = UUID.randomUUID().toString();
        System.out.println("string = " + string);
    }
}
