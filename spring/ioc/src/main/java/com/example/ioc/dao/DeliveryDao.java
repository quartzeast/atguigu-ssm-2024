package com.example.ioc.dao;

import com.example.ioc.datasource.MyDataSource;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
public class DeliveryDao {
    @Autowired
    MyDataSource myDataSource;

    public void saveDelivery() {
        System.out.println("数据源: " + myDataSource);
    }
}
