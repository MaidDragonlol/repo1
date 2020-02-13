package com.example.demo.controller;

import com.example.demo.domain.StudentDomain;
import com.example.demo.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchOneMany {
    @Autowired
    private StudentMapper studentMapper;
    @RequestMapping(value = "/getSubjectWithStuid", method = RequestMethod.POST)
    public StudentDomain getSubjectWithStuid(String stuid) {
        StudentDomain stuname = studentMapper.selectUserById(Integer.parseInt(stuid));
        return stuname;
    }
}
