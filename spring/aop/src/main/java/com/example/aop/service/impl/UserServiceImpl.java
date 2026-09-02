package com.example.aop.service.impl;

import com.example.aop.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public void saveUser() {
        System.out.println("saveUser");
    }
}
