package com.pyp.myspring.entity;

import com.pyp.myspring.Autowired;
import com.pyp.myspring.Component;
import com.pyp.myspring.Qualifier;
import com.pyp.myspring.Value;
import lombok.Data;

@Data
@Component("a")
public class Account {
    @Value("1")
    private Integer id;
    @Value("张三")
    private String name;
    @Value("20")
    private Integer age;
    @Autowired
    @Qualifier("myOrder")
    private Order order;
}
