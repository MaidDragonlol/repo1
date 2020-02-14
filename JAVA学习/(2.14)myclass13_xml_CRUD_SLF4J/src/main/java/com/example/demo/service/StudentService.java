package com.example.demo.service;

import com.example.demo.domain.StudentDomain;
import com.example.demo.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        public List<StudentDomain> ListStudnt(){
            return	studentMapper.ListStudnt();
        }


        public int Update(StudentDomain studentDomain){
            return studentMapper.Update(studentDomain);
        }

        public int delete(int id){
            return studentMapper.delete(id);
        }
    }

