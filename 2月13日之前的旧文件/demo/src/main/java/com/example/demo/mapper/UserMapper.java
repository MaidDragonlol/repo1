package com.example.demo.mapper;
import com.example.demo.domain.User;
import java.util.List;

@Mapper  //难受了。。。显红识别不了？
public interface UserMapper { List<User> queryUserList();}
