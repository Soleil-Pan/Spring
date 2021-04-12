package com.pyp.spring.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component("myorder")  //修改bean的名字
public class Order {
    @Value("123")
    private String orderId;
    @Value("1000.00")
    private float price;
}
