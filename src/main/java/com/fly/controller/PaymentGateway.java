package com.fly.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentGateway {


    @RequestMapping("hello")
    public String sayHello(){
        return ("Hello,");
    }
    
    
    
}