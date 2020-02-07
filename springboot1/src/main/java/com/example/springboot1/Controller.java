package com.example.springboot1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@org.springframework.stereotype.Controller
public class Controller {

    @RequestMapping("/quick")
    @ResponseBody
    public String quick(){
        return "springboot 搞半天你终于可以了。。。";
    }

}
