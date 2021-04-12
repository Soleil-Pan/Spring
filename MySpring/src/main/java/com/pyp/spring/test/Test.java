package com.pyp.spring.test;

import javafx.application.Application;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        //加载IoC容器
        //a.基于xml加载IoC容器
//      ApplicationContext applicationContext = new ClassPathXmlApplicationContext("com.pyp.spring.entity");
        //b.基于注解加载IoC
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.pyp.spring.entity");
        //小写的全类名
        //System.out.println(applicationContext.getBean("account"));
        //获取所有bean的名字
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        //获取所有bean的数量
        System.out.println(applicationContext.getBeanDefinitionCount());
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
            //通过名字取出所有的bean
            System.out.println(applicationContext.getBean(beanDefinitionName));
        }
    }
}
