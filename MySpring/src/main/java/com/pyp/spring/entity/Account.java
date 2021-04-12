package com.pyp.spring.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component /*标记类注解到IOC中*/
public class Account {
    @Value("1")
    private Integer id;
    @Value("zhangsan")
    private String name;
    @Value("20")
    private Integer age;
//   @Autowired /*根据bean类型自动装载*/
//   @Qualifier("myorder") /*根据bean名字自动装载*/
    private Order order;
}
