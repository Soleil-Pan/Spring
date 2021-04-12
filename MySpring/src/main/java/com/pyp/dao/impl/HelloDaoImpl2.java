package com.pyp.dao.impl;

import com.pyp.dao.HelloDao;

import java.util.Arrays;
import java.util.List;

public class HelloDaoImpl2 implements HelloDao {
    @Override
    public List<String> fingAll() {
        return Arrays.asList("4","5","6");
    }
}
