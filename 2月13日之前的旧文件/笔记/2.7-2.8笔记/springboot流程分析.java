helloword示例

1.创建pom.xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>cn.ly</groupId>
	<artifactId>springboot01</artifactId>
	<version>0.0.1-SNAPSHOT</version>
 
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>1.5.9.RELEASE</version>
	</parent>
 
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
	</dependencies>	
</project>
2.创建controller
package cn.ly.helloWorld;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
public class HelloController {
 
    @RequestMapping("/hello")
    public String hello(){
        return "Hello World!";
    }
}
3.创建spring boot启动类
package cn.ly;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
 
/**
 *  @SpringBootApplication 来标注一个主程序类，说明这是一个Spring Boot应用
 */
@SpringBootApplication
public class HelloWorldMainApplication {
 
    public static void main(String[] args) {
 
        // Spring应用启动起来
        SpringApplication.run(HelloWorldMainApplication.class,args);
    }
}
4.http://localhost:8080/hello访问
流程分析
1.@SpringBootApplication

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = {
		@Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
		@Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
public @interface SpringBootApplication {
1.1@SpringBootConfiguration:Spring Boot的配置类

1.1.1
@Configuration:配置类上来标注这个注解；
配置类 ----- 配置文件；配置类也是容器中的一个组件；@Component
1.2 @EnableAutoConfiguration开启自动配置功能
以前我们需要配置的东西，Spring Boot帮我们自动配置；@EnableAutoConfiguration告诉SpringBoot开启自动配置功能；这样自动配置才能生效；
@AutoConfigurationPackage
@Import(EnableAutoConfigurationImportSelector.class)
public @interface EnableAutoConfiguration {
1.2.1@AutoConfigurationPackage：自动配置包
1.2.1.1@Import(AutoConfigurationPackages.Registrar.class)： Spring的底层注解@Import，给容器中导入一个组件；点开这里类可以看到AutoConfigurationPackages.Registrar.class；的工作是将主配置类（@SpringBootApplication标注的类）的所在包及下面所有子包里面的所有组件扫描到Spring容器；
1.2.1.2@Import(EnableAutoConfigurationImportSelector.class)；
给容器中导入组件
EnableAutoConfigurationImportSelector：导入哪些组件的选择器；
将所有需要导入的组件以全类名的方式返回；这些组件就会被添加到容器中；
会给容器中导入非常多的自动配置类（xxxAutoConfiguration）；就是给容器中导入这个场景需要的所有组件，并配置好这些组件；
有了自动配置类，免去了我们手动编写配置注入功能组件等的工作。
