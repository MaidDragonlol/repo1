package com.example.demo.service;

import com.example.demo.domain.StudentDomain;
import com.example.demo.mapper.StudentMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
public class ServiceText {
    private StudentMapper studentMapper;
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    public void addUsertest(StudentDomain classstudent) {
        studentMapper.addUser(classstudent);
    }
    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    public void addUser(StudentDomain classstudent) {
        studentMapper.addUser(classstudent);
    }                                                    /*示例：localhost:8080//addStudent?stuid=1234&stuname=桔梗&stusex=0&stubirth=1760-12-6*/
    @RequestMapping(value = "/deleteStudent", method = RequestMethod.POST)
    public void deleteUser(String stuid) {
        studentMapper.deleteUser(Integer.parseInt(stuid));
    }                                                    /*示例：localhost:8080//deleteStudent?stuid=1007*/

    @RequestMapping(value = "/updateStudent", method = RequestMethod.POST)
    public void updateUser(StudentDomain studentDomain) {
        studentMapper.updateUser(studentDomain);
                                                        /*示例：localhost:8080//updateStudent?stuname=古河渚&stuid=1007*/
    }
    /*@RequestMapping(value = "/updateStudent", method = RequestMethod.POST)
    public void updateUser(StudentDomain studentDomain) {
        studentmapper.updateUser(studentDomain);
    } */                                                /*示例：localhost:8080//updateStudent?stuid=1006&stuname=发无缺&stusex=1&stubirth=1976-4-3*/


}
