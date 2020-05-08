package com.example.demo.controller;

import com.example.demo.domain.StudentDomain;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(value = "/CRUD", method = { RequestMethod.GET, RequestMethod.POST })
public class StudentListController {
    @Autowired
    private StudentService studentService;
private StudentMapper studentMapper;
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
    public Object insertLabel(@RequestParam("list") List<StudentDomain> list, Integer stuid, String stuname, Character  stusex,String stubirth) {
        studentMapper.batchInsertStudents(list);
        for(StudentDomain  StudentDomain: list){
            System.out.println(StudentDomain.getStuid());
        }
        return "ok";
    }
    /*批量更新*/
    @PostMapping("/UpdateAll")
    public Object updateLabel(@RequestParam("list") List<StudentDomain> list, Integer stuid, String stuname, Character  stusex,String stubirth) {
        studentMapper.updateBatch(list);
        for(StudentDomain  StudentDomain: list){
            System.out.println(StudentDomain.getStuid());
        }
        return "ok";
    }
    /*条件查询*/
    @RequestMapping("/ListUserByIf")
    public List<StudentDomain> findByName(StudentDomain studentDomain) {
        return studentMapper.findByUser(studentDomain);
    }


}
