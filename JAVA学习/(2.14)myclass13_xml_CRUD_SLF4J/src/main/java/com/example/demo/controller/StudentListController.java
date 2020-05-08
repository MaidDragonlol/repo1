package com.example.demo.controller;

import com.example.demo.domain.StudentDomain;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping(value = "/CRUD", method = { RequestMethod.GET, RequestMethod.POST })
public class StudentListController {
        @Autowired
    private StudentService studentService;
        @RequestMapping(value = "/delete", method = RequestMethod.GET)
        public String delete(int stuid) {
            int result = studentService.delete(stuid);
            if (result >= 1) {
                return "删除成功";
            } else {
                return "删除失败";
            }
        }

        @RequestMapping(value = "/update", method = RequestMethod.POST)
        public String update(StudentDomain studentDomain) {
            int result = studentService.Update(studentDomain);
            if (result >= 1) {
                return "修改成功";
            } else {
                return "修改失败";
            }

        }

        @RequestMapping(value = "/insert", method = RequestMethod.POST)
        public StudentDomain insert(StudentDomain studentDomain) {
            return studentService.insertStudnt(studentDomain);
        }

        @RequestMapping("/ListStudent")

        public List<StudentDomain> ListStudnt(){
            return studentService.ListStudnt();
        }

        @RequestMapping("/ListUserByStuName")

        public List<StudentDomain> ListStudntByname(String stuname){
            return studentService.findByName(stuname);
        }
    }

