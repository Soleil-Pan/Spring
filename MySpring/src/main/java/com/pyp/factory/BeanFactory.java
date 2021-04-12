package com.pyp.factory;

import com.pyp.dao.HelloDao;
import com.pyp.dao.impl.HelloDaoImpl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class BeanFactory {

    private static Properties properties;
    private static Map<String,Object> cache = new HashMap<>();

    static {
        properties = new Properties();
        try {
            properties.load(BeanFactory.class.getClassLoader().getResourceAsStream("factory.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*多态工厂,返回父类对象*/
    public static Object getDao(String beanName){
        //先判断缓存中是否存在bean
        //判断Map中是否存在某个key值
        if(!cache.containsKey(beanName)){
            //保证多线程下也是单例模式，使用锁
            synchronized (BeanFactory.class){
                //双重检测
                if (!cache.containsKey(beanName)){
                    try {
                        String value = properties.getProperty(beanName);
                        //反射机制创建对象
                        Class clazz = Class.forName(value);
                        Object object = clazz.getConstructor(null).newInstance(null);
                        //将bean存入缓存
                        cache.put(beanName,object);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return cache.get(beanName);
    }
}
