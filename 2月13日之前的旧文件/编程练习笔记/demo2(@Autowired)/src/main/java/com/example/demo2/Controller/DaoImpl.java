package com.example.demo2.Controller;

import org.springframework.stereotype.Repository;

@Repository
public class DaoImpl implements IDao{
    @Override  //@Override是伪代码,表示重写。

    public String sayHello() {
        System.out.println("Say Hello From DaoImpl");
        return null;
    }
}
