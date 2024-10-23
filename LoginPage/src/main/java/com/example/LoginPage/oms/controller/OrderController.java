package com.example.LoginPage.oms.controller;

import com.example.LoginPage.oms.entity.CilOrderInfo;
import com.example.LoginPage.oms.impl.OrderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderImpl orderImpl;
    @PostMapping("/add")
    public void createOrder(@RequestBody CilOrderInfo cilOrderInfo){
        orderImpl.createOrder(cilOrderInfo);
    }
}
