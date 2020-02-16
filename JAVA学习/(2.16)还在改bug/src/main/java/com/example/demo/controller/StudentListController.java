package com.example.demo.controller;

import com.example.demo.domain.StudentDomain;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(value = "/CRUD", method = {RequestMethod.GET, RequestMethod.POST})
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

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public StudentDomain insert(StudentDomain studentDomain) {
        return studentService.insertStudnt(studentDomain);
    }

    @RequestMapping("/ListStudent")

    public List<StudentDomain> ListStudnt() {
        return studentService.ListStudnt();
    }

    @RequestMapping("/ListUserByStuName")

    public List<StudentDomain> ListStudntByname(String stuname) {
        return studentService.findByName(stuname);
    }

    /*批量插入*/
    @PostMapping("/insertAll")//@RequestParam 不写会报List错误
    public int batchSelect(List<StudentDomain> list) {
        return studentService.batchInsert(list);
    }

    /*批量更新*/
    @PostMapping("/UpdateAll")
    public int batchUpdate(List<StudentDomain> list) {
        return studentService.batchUpdate(list);
    }

    /*条件查询*/
    @RequestMapping("/ListUserByIf")
    public List<StudentDomain> findByName(StudentDomain studentDomain) {
        return studentService.findByUser(studentDomain);
    }

    /*一对一*/
    @RequestMapping("/ListUserByIf")
    public List<StudentDomain> findByStidAccount(int stuid) {
        return studentService.testfindOrdersList(stuid);
    }

}
