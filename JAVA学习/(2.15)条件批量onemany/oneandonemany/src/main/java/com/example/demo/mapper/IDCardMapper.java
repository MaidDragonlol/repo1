package com.example.demo.mapper;

import com.example.demo.Domain.IDCard;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IDCardMapper {
    public List<IDCard> selectIdOfUser();
}
