package com.example.demo.mapper;


import com.example.demo.domain.StudentDomain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper  //映射不能忘了Mapper
public interface StudentMapper {

    List<StudentDomain> findStudntByName(String stuname);

    List<StudentDomain> ListStudnt();

    int insertStudnt(StudentDomain StudentDomain);

    int delete(int stuid);

    int Update(StudentDomain StudentDomain);

}


