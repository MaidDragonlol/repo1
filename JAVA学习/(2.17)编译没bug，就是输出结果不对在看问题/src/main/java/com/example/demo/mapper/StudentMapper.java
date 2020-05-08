package com.example.demo.mapper;


import com.example.demo.domain.ListInput;
import com.example.demo.domain.StudentDomain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper  //映射不能忘了Mapper
public interface StudentMapper {

    List<StudentDomain> findStudntByName(String stuname);

    List<StudentDomain> ListStudnt();

    int insertStudnt(StudentDomain studentDomain);

    int delete(int stuid);
   /*批量插入*/
   int batchInsert(List<StudentDomain> list);//批量插入
  /*  批量更新*/
  int batchUpdate(List<StudentDomain> list);//批量更新
/*动态aql之<if>*/
List<StudentDomain> findByUser(StudentDomain studentDomain);
/*查询stuid集合*/
    List<StudentDomain> findInIds(ListInput listInput);
    /*查询stuid集合*/
    List<StudentDomain> findAllAccount(int stuid);
/*一对一*/
List<StudentDomain> findAllAccountByid(int stuid);
}


