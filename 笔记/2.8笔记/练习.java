使用注解@Value映射 
application.properties配置如下：
 person:  name: zhangsan  age: 18
或者，application.yml配置如下：
 person:  name: zhangsan  age: 18
实体Bean代码如下：
 @Controller public class QuickStartController {
 
    @Value("${person.name}")    private String name;    
	@Value("${person.age}")    private Integer age;
    @RequestMapping("/quick")    @ResponseBody    public String quick(){        return "springboot 访问成功! name="+name+",age="+age;    }
 
}
浏览器访问地址：http://localhost:8080/quick
