package com.pyp.myspring.entity;

import com.pyp.myspring.Component;
import com.pyp.myspring.Value;
import lombok.Data;

@Data
@Component("myOrder")
public class Order {
    @Value("xxx123")
    private String orderId;
    @Value("1000.00")
    private Float price;
}
