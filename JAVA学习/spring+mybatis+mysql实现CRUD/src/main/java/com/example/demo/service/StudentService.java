package com.example.demo.service;

import com.example.demo.domain.StudentDomain;
import com.example.demo.mapper.StudentMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        studentMapper.insertStudent(studentDomain);
        return studentDomain;
    }

    public List<StudentDomain> ListStudnt() {
        return studentMapper.ListStudnt();
    }

/*删除*/
    public int delete(int id) {
        return studentMapper.delete(id);
    }

    /*批量插入*/
    public int batchInsert( List<StudentDomain> list){
        return studentMapper.batchInsert(list);
    }
    /*批量更新*/
     public int batchUpdate( List<StudentDomain> list){
         return studentMapper.batchUpdate(list);
     };
    /*for(数据类型 变量名: 数组名或集合名){
        使用变量即可，该变量就是元素
    }*/
    /*条件查询*/
    public List<StudentDomain> findByUser(StudentDomain studentDomain) {
        return studentMapper.findByUser(studentDomain);
    }
/*一对一*/
    public List<StudentDomain> testfindOrdersList(int stuid){
        return studentMapper.findAllAccountByid(stuid);

    }



}
