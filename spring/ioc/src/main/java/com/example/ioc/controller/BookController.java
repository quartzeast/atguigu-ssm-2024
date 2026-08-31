package com.example.ioc.controller;

import com.example.ioc.bean.Book;
import jakarta.annotation.Resource;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Data
@Controller
/*
    @Autowired 和 @Resource 都可以完成自动注入，主要区别如下：
    1. 来源不同：@Autowired 是 Spring 提供的注解，@Resource 是 Jakarta 标准注解。
    2. 查找规则不同：@Autowired 默认先按类型查找，再按名称匹配；@Resource 默认先按名称查找，再按类型查找。
    3. 指定名称的方式不同：@Autowired 通常配合 @Qualifier 指定 Bean 名称；@Resource 直接通过 name 属性指定。
    4. @Autowired 支持 @Primary 选择同类型 Bean 的默认实现；@Resource 更适合明确按 Bean 名称注入。
*/
public class BookController {

    // @Autowired：先按 Book 类型查找，多个候选时使用 @Primary 指定的 Bean
    @Autowired
    private Book primaryBook;

    // @Autowired + @Qualifier：按 Bean 名称精确注入
    @Autowired
    @Qualifier("javaBook")
    private Book javaBook;

    // @Resource：默认先按照字段名 mysqlBook 查找 Bean
    @Resource
    private Book mysqlBook;

    // @Resource(name)：直接按照指定的 Bean 名称查找
    @Resource(name = "goBook")
    private Book goBook;
}
