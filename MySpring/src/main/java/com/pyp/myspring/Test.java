package com.pyp.myspring;

public class Test {
    public static void main(String[] args) {
        MyAnnotationConfigApplicationContext applicationContext = new MyAnnotationConfigApplicationContext("com.pyp.myspring.entity");
        System.out.println(applicationContext.getBean("a"));
        //System.out.println(applicationContext.getBean("myOrder"));
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
            System.out.println(applicationContext.getBean(beanDefinitionName));
        }
    }
}
