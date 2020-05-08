package com.example.demo.mapper;


import com.example.demo.domain.ClassDomain;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ClassMapper {

    @Select("Select * from classtable where stuid = #{stuid}")
     ClassDomain getByClass(int stuid);
}
