package com.example.demo.controller;

import com.example.demo.mapper.Studentmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public class Controller {
    @Autowired
    private Studentmapper studentmapper;

    @RequestMapping("/student")
    @ResponseBody
    public List<Class> studentlist() {
        List<Class> classlist = studentmapper.studentlist();
        return classlist;
    }
}
