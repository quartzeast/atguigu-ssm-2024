package com.example.ioc.controller;

import com.example.ioc.bean.Device;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Data
@Controller
public class DeviceController {

    @Autowired
    private Device device;
}
