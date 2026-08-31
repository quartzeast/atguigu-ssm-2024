package com.example.ioc.controller;

import com.example.ioc.bean.Account;
import com.example.ioc.service.AccountService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;


@Data
@Controller
public class AccountController {
    /*
        自动注入流程（先按照类型，再按照名称）
        1. 根据类型，查找对应的组件
            有且只有一个，则直接注入
            如果找到多个，则再按名称查找，默认将成员变量名当做组件名进行匹配查找
     */

    @Autowired
    private AccountService accountService;

    @Autowired
    private Account personalAccount;

    // 将某类型的所有组件批量注入到 List 集合中
    @Autowired
    private List<Account> accounts;

    // Map 的 key 是 Bean 名称
    @Autowired
    private Map<String, Account> accountMap;

    // 注入 ioc 容器自身
    @Autowired
    ApplicationContext applicationContext;
}
