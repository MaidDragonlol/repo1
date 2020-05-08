package com.example.demo.service;

import com.example.demo.domain.StudentDomain;
import com.example.demo.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;

    public List<StudentDomain> findByName(String stuname) {
        return studentMapper.findStudntByName(stuname);
    }

    public StudentDomain insertStudnt(StudentDomain studentDomain) {
        studentMapper.insertStudnt(studentDomain);
        return studentDomain;
    }

    public List<StudentDomain> ListStudnt() {
        return studentMapper.ListStudnt();
    }



    public int delete(int id) {
        return studentMapper.delete(id);
    }

    /*批量插入*/
    /*for(数据类型 变量名: 数组名或集合名){
        使用变量即可，该变量就是元素
    }*/


}
