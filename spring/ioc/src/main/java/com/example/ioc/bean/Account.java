package com.example.ioc.bean;

import lombok.Data;

@Data
public class Account {
    private String accountNo;

    public Account(String accountNo) {
        this.accountNo = accountNo;
    }
}
