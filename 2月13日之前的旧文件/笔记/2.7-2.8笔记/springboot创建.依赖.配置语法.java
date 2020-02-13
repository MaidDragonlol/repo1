//1，添加SpringBoot的起步依赖
<parent>    <groupId>org.springframework.boot</groupId>    <artifactId>spring-boot-starter-parent</artifactId>    <version>2.0.1.RELEASE</version> </parent>
//2，SpringBoot要集成SpringMVC进行Controller的开发，所以项目要导入web的启动依赖
<dependencies>    <dependency>        <groupId>org.springframework.boot</groupId>        <artifactId>spring-boot-starter-web</artifactId>    </dependency> </dependencies>
//3，编写SpringBoot引导类
//示例
package com.hello;
import org.springframework.boot.SpringApplication; import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication //@SpringBootApplication：标注SpringBoot的启动类，该注解具备多种功能
public class MySpringBootApplication {
 
    public static void main(String[] args) { SpringApplication.run(MySpringBootApplication.class);    }
	//SpringApplication.run(MySpringBootApplication.class) 代表运行SpringBoot的启动类，参数为SpringBoot 启动类的字节码对象
}
//4,编写Controller
       @Controller
//5,<!--热部署配置-->
<dependency>    <groupId>org.springframework.boot</groupId>    <artifactId>spring-boot-devtools</artifactId> </dependency>
//6,按住Ctrl点击parent可查看具体配置，从上面的spring-boot-starter-web的pom.xml中我们可以发现，spring-boot-starter-web就是将web开发要使用的 spring-web、spring-webmvc等坐标进行了“打包”，
     //这样我们的工程只要引入spring-boot-starter-web起步依赖的 坐标就可以进行web开发了，同样体现了依赖传递的作用
//7，SpringBoot配置文件类型和作用（properties、yml）
//7.1,YML文件的扩展名可以使用.yml或者.yaml
//7.2yml配置文件的语法
A.配置普通数据 
语法： key: value 示例代码：
 name: haoha
注意：value之前有一个空格
B.配置对象数据 
语法：
  key:
  key1: value1
  key2: value2
  或者：
  key: {key1: value1,key2: value2} 示例代码：
 person:  name: haohao  age: 31  addr: beijing
#或者
person: {name: haohao,age: 31,addr: beijing}
注意：key1前面的空格个数不限定，在yml语法中，相同缩进代表同一个级别
C. 配置Map数据 
同上面的对象写法
D.配置数组（List、Set）数据 
语法：
  key:
  - value1
  - value2 或者：
  key: [value1,value2] 
city:  - beijing  - tianjin  - shanghai  - chongqing  #或者 
city: [beijing,tianjin,shanghai,chongqing]
#集合中的元素是对象形式 
student:  - name: zhangsan    age: 18    score: 100  - name: lisi    age: 28    score: 88  - name: wangwu    age: 38    score: 90
注意：value1与之间的 - 之间存在一个空格 
注意事项：
1.缩进不允许使用tab只能使用空格
2.数据格式为，名称:(空格)值
3.空格的个数不重要，只要相同层级的元素左对齐即可
4.注释用“#”号
5.大小写敏感
6.相同上级的并在一起向下写，不要另写一行。
