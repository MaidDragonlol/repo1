package com.example.demo2;

import com.example.demo2.Controller.DaoImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DaoImplTests {
    @Autowired
    private DaoImpl daoimpl;

    @Test
    public void test() {
        daoimpl.sayHello();

    }
}
