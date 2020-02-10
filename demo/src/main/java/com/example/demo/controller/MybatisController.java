package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MybatisController {
    @Qualifier(value = "UserMapper")   //为什么说找不到我的UserMapper啊，恼火
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/query")
    @ResponseBody
    public List<User> queryUserList() {
        List<User> users = userMapper.queryUserList();
        return users;
    }

}
