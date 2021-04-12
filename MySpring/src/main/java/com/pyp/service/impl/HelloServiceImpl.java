package com.pyp.service.impl;

import com.pyp.dao.HelloDao;
import com.pyp.dao.impl.HelloDaoImpl;
import com.pyp.dao.impl.HelloDaoImpl2;
import com.pyp.factory.BeanFactory;
import com.pyp.service.HelloService;
import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.List;

public class HelloServiceImpl implements HelloService {

    public HelloServiceImpl() {
        for (int i = 0; i < 10; i++) {
            System.out.println(BeanFactory.getDao("helloDao"));
        }
    }

    private HelloDao helloDao = (HelloDao) BeanFactory.getDao("helloDao");

    @Override
    public List<String> findAll() {
        return this.helloDao.fingAll();
    }
}
