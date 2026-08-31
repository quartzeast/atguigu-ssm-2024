package com.example.ioc.controller;

import com.example.ioc.bean.Category;
import com.example.ioc.bean.Product;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Data
@Controller
public class ProductController {

    // 构造器注入：创建 Controller 时注入必需的商品
    private final Product product;

    // Setter 注入：Controller 创建后注入商品分类
    private Category category;

    @Autowired
    public ProductController(Product product) {
        this.product = product;
    }

    @Autowired
    public void setCategory(Category category) {
        this.category = category;
    }
}
