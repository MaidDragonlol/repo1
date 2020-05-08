package com.example.demo.mapper;


import com.example.demo.domain.ListInput;
import com.example.demo.domain.StudentDomain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper  //映射不能忘了Mapper
public interface StudentMapper {

    List<StudentDomain> findStudntByName(String stuname);

    List<StudentDomain> ListStudnt();

    int insertStudnt(StudentDomain studentDomain);

    int delete(int stuid);
   /*批量插入*/
    void batchInsertStudents(@Param("list") List<StudentDomain> studentDomains);
  /*  批量更新*/
  void updateBatch(@Param ("list") List<StudentDomain> studentDomains);
/*动态aql之<if>*/
List<StudentDomain> findByUser(StudentDomain studentDomain);
/*查询stuid集合*/
    List<StudentDomain> findInIds(ListInput listInput);
}


