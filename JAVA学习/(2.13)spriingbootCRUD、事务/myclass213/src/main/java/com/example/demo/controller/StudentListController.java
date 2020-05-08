package com.example.demo.controller;

import com.example.demo.domain.StudentDomain;
import com.example.demo.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class StudentListController {
    @Autowired
    private StudentMapper studentMapper;

    @RequestMapping(value = "/selectStudentById", method = RequestMethod.POST)
    public StudentDomain selectUserById(String stuid) {
        StudentDomain stuname = studentMapper.selectUserById(Integer.parseInt(stuid));
        return stuname;
    }                                                    /*示例：localhost:8080//selectStudentById?stuid=1003*/

    @RequestMapping(value = "/selectStudentByName", method = RequestMethod.POST)
    public List<StudentDomain> selectUserByName(String stuname) {
        return studentMapper.selectUserByName(stuname);
    }                                                    /*示例：localhost:8080//selectStudentByName?stuname=令狐冲*/

     }