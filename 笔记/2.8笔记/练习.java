ʹ��ע��@Valueӳ�� 
application.properties�������£�
 person:  name: zhangsan  age: 18
���ߣ�application.yml�������£�
 person:  name: zhangsan  age: 18
ʵ��Bean�������£�
 @Controller public class QuickStartController {
 
    @Value("${person.name}")    private String name;    
	@Value("${person.age}")    private Integer age;
    @RequestMapping("/quick")    @ResponseBody    public String quick(){        return "springboot ���ʳɹ�! name="+name+",age="+age;    }
 
}
��������ʵ�ַ��http://localhost:8080/quick
