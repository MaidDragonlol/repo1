package com.example.demo.controller;

import com.example.demo.domain.Schoolclass;
import com.example.demo.mapper.SchoolclassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MybatisController {
    @Autowired
    private SchoolclassMapper schoolclassMapper;

    @RequestMapping("/class")
    @ResponseBody
    public List<Schoolclass> liststudent() {
        List<Schoolclass> lists = schoolclassMapper.liststudent();
        return lists;
    }

}
