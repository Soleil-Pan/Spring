package com.pyp.myspring;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor //带参构造
public class BeanDefinition {
    private String beanName;
    private Class beanClass;
}
