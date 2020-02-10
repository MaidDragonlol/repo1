package feb9.demo.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@RestController

public class paramsA {
    /*@RequestMapping(value="/get/{id:\\d+}") //后面那个是一个正则表达式这样的话只能get后面是数字，否则就会报4xx错误，找不到这个路径。*/
   /* @RequestMapping(value = "/users/{username}")//情况1：输出"user" + username*/
    /*@RequestMapping路径正则匹配指定参数
    @RequestMapping(value = "${api.version}/step/{param1:^day\b|^week\b|^month\b|^year\b}")*/
    @RequestMapping(value = "/users/{username}",method = RequestMethod.GET, params="myParam=myValue")
    /*params:指定request中必须包含某些参数值是，才让该方法处理。
    仅处理请求中包含了名为“myParam”，值为“myValue”的请求*/
    @ResponseBody
    public String userProfile(@PathVariable String username){
        return "user" + username;
    }
    @RequestMapping(value = "/users/{username}",method = RequestMethod.GET, headers="Referer=http://www.111.com/")
    /*仅处理request的header中包含了指定“Refer”请求头和对应值为“http://www.111.com/”的请求*/
    @ResponseBody
    public String userProfil(@PathVariable String username){
        return "user" + username;
    }

}




