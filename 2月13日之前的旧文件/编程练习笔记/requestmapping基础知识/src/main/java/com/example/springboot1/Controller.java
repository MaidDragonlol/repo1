package com.example.springboot1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.*;

@ConfigurationProperties(prefix = "person")
@org.springframework.stereotype.Controller
@RestController
public class Controller {
    private String name;    private Integer age;
   /*@RequestMapping("/hello")有8个属性。
    value：指定请求的实际地址。
    method：指定请求的method类型（GET,POST,PUT,DELETE）等。
    consumes：指定处理请求的提交内容类型（Context-Type）。
    produces：指定返回的内容类型，还可以设置返回值的字符编码。
    params：指定request中必须包含某些参数值，才让该方法处理。
    headers：指定request中必须包含某些指定的header值，才让该方法处理请求
    @getMapping = @requestMapping(method = RequestMethod.GET)。*/
    @GetMapping(value = "text1")
    public String text1(){return "hello1";}
    @PostMapping(value = "text2")
    public String text2(){return "hello2";}
    /*@postMapping = @requestMapping(method = RequestMethod.POST)。*/
    //@PostMapping(value = "/hello")  //没有结果
    /*get一般用于向服务器请求获取数据，请求参数存放在URL中，并在地址栏可见，而post是向服务器提交数据，数据放置在容器（HTML HEADER）内且不可见*/
    /*get方式提交的数据最多只能有1024字节，而post则没有此限制；*/
    @ResponseBody
    public String quick(){        return "springboot 访问成功! name="+name+",age="+age;    }

    public void setName(String name) {        this.name = name;    }

    public void setAge(Integer age) {        this.age = age;    }
}
