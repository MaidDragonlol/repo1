package com.example.demo.mapper;

import com.example.demo.domain.ClassDomain;
import com.example.demo.domain.StudentDomain;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper  //映射不能忘了Mapper
public interface StudentMapper {

    @Select("select * from studenttable where stuid=#{stuid}")
    StudentDomain selectUserById(int stuid);

    @Select("select * from studenttable where stuname=#{stuname}")
     List<StudentDomain> selectUserByName(String stuname);
    @Insert("insert into studenttable(stuid,stuname,stusex,stubirth) values(#{stuid},#{stuname},#{stusex},#{stubirth})")
     void addUser(StudentDomain classstudent);

    @Delete("delete from studenttable where stuid=#{stuid}")
     void deleteUser(int stuid);

    /*@Update("update studenttable set stuid=#{stuid},stuname=#{stuname},stusex=#{stusex},stubirth=#{stubirth}")
     void updateUser(StudentDomain classstudent);*//*这个不好，因为它把表中所以数据都改了*/
    @Update("update studenttable set stuname = #{stuname} where stuid = #{stuid}")
    void updateUser(StudentDomain classstudent);   /*这个是修改对应数据*/

    @Select("select * from studenttable where stuid = #{stuid}")
    @Results({
            @Result(property="classDomain",column="stuid",one=@One(select="com.example.demo.mapper.ClassMapper.getByClass"))
    })
     StudentDomain getSubjectWithStuid(int stuid);   //如果是多行数据 用many=@Many 一对一用one=@One

}
