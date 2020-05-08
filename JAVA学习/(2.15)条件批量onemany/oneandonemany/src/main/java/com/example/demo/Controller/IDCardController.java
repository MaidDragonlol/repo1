package com.example.demo.Controller;

import com.example.demo.Domain.IDCard;
import com.example.demo.mapper.IDCardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class IDCardController {

    @Autowired
    private IDCardMapper idCardMapper;


    @GetMapping("/selectIdOfUser")
    public List<IDCard> listOfUser(){
        List<IDCard> idCards = idCardMapper.selectIdOfUser();
        return idCards;
    }
}