package com.pyp.dao.impl;

import com.pyp.dao.HelloDao;

import java.util.Arrays;
import java.util.List;

public class HelloDaoImpl implements HelloDao {
    @Override
    public List<String> fingAll() {
        return Arrays.asList("1","2","3");
    }
}
