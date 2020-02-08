hellowordʾ��

1.����pom.xml
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
2.����controller
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
3.����spring boot������
package cn.ly;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
 
/**
 *  @SpringBootApplication ����עһ���������࣬˵������һ��Spring BootӦ��
 */
@SpringBootApplication
public class HelloWorldMainApplication {
 
    public static void main(String[] args) {
 
        // SpringӦ����������
        SpringApplication.run(HelloWorldMainApplication.class,args);
    }
}
4.http://localhost:8080/hello����
���̷���
1.@SpringBootApplication

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = {
		@Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
		@Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
public @interface SpringBootApplication {
1.1@SpringBootConfiguration:Spring Boot��������

1.1.1
@Configuration:������������ע���ע�⣻
������ ----- �����ļ���������Ҳ�������е�һ�������@Component
1.2 @EnableAutoConfiguration�����Զ����ù���
��ǰ������Ҫ���õĶ�����Spring Boot�������Զ����ã�@EnableAutoConfiguration����SpringBoot�����Զ����ù��ܣ������Զ����ò�����Ч��
@AutoConfigurationPackage
@Import(EnableAutoConfigurationImportSelector.class)
public @interface EnableAutoConfiguration {
1.2.1@AutoConfigurationPackage���Զ����ð�
1.2.1.1@Import(AutoConfigurationPackages.Registrar.class)�� Spring�ĵײ�ע��@Import���������е���һ��������㿪��������Կ���AutoConfigurationPackages.Registrar.class���Ĺ����ǽ��������ࣨ@SpringBootApplication��ע���ࣩ�����ڰ������������Ӱ�������������ɨ�赽Spring������
1.2.1.2@Import(EnableAutoConfigurationImportSelector.class)��
�������е������
EnableAutoConfigurationImportSelector��������Щ�����ѡ������
��������Ҫ����������ȫ�����ķ�ʽ���أ���Щ����ͻᱻ��ӵ������У�
��������е���ǳ�����Զ������ࣨxxxAutoConfiguration�������Ǹ������е������������Ҫ����������������ú���Щ�����
�����Զ������࣬��ȥ�������ֶ���д����ע�빦������ȵĹ�����
