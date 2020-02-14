package com.example.demo.controller;

import com.example.demo.domain.StudentDomain;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchOneMany {
    @Autowired

    private StudentService studentService;
    private StudentMapper studentMapper;
    @RequestMapping("/ListUserBynameonemany")
    public List<StudentDomain> ListStudntByname(String stuname){
        return studentService.findByName(stuname);
    }
}
