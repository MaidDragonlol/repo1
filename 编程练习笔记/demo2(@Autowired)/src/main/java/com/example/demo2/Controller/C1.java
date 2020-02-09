package com.example.demo2.Controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class C1 {
    @RequestMapping(value = "/show1")  //RequestMapping是一个用来处理请求地址映射的注解，可用于类或方法上。用于类上，表示类中的所有响应请求的方法都是以该地址作为父路径。
    //@RequestMapping("/Student");==@RequestMapping(value="/Student");
    // @RequestMapping(value = {"/say","hi"},method = RequestMethod.GET)，这里面的say和hi是或的关系，输入其中的任何一个都能访问此接口
    @ResponseBody  //@ResponseBody的作用其实是将java对象转为json格式的数据。
    public String printHello() {
        return "hello";
    }
    @Autowired    //@Autowired 注释，它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作。
    private DaoImpl daoimpl;  //装配的成员变量是daoimpl,使得daoimpl虽然是priva变量，没有setter和getter也能在其他方法中调用

    @Test  //Junit单元测试
    public void test() {
        daoimpl.sayHello();
    }

}

