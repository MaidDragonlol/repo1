package com.example.demo.mapper;


import com.example.demo.domain.Schoolclass;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SchoolclassMapper {
     List<Schoolclass> liststudent();
}
