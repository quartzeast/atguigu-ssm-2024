package com.example.aop.service.impl;

import com.example.aop.annotation.MyAnnotation;
import com.example.aop.service.UserService;
import org.springframework.stereotype.Service;

@MyAnnotation
@Service
public class UserServiceImpl implements UserService {

    @Override
    public void saveUser() {
        System.out.println("saveUser");
    }

    @MyAnnotation
    @Override
    public void updateUser() {
        System.out.println("updateUser");
    }
}
