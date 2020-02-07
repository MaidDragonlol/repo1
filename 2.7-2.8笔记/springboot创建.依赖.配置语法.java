//1�����SpringBoot��������
<parent>    <groupId>org.springframework.boot</groupId>    <artifactId>spring-boot-starter-parent</artifactId>    <version>2.0.1.RELEASE</version> </parent>
//2��SpringBootҪ����SpringMVC����Controller�Ŀ�����������ĿҪ����web����������
<dependencies>    <dependency>        <groupId>org.springframework.boot</groupId>        <artifactId>spring-boot-starter-web</artifactId>    </dependency> </dependencies>
//3����дSpringBoot������
//ʾ��
package com.hello;
import org.springframework.boot.SpringApplication; import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication //@SpringBootApplication����עSpringBoot�������࣬��ע��߱����ֹ���
public class MySpringBootApplication {
 
    public static void main(String[] args) { SpringApplication.run(MySpringBootApplication.class);    }
	//SpringApplication.run(MySpringBootApplication.class) ��������SpringBoot�������࣬����ΪSpringBoot ��������ֽ������
}
//4,��дController
       @Controller
//5,<!--�Ȳ�������-->
<dependency>    <groupId>org.springframework.boot</groupId>    <artifactId>spring-boot-devtools</artifactId> </dependency>
//6,��סCtrl���parent�ɲ鿴�������ã��������spring-boot-starter-web��pom.xml�����ǿ��Է��֣�spring-boot-starter-web���ǽ�web����Ҫʹ�õ� spring-web��spring-webmvc����������ˡ��������
     //�������ǵĹ���ֻҪ����spring-boot-starter-web�������� ����Ϳ��Խ���web�����ˣ�ͬ���������������ݵ�����
//7��SpringBoot�����ļ����ͺ����ã�properties��yml��
//7.1,YML�ļ�����չ������ʹ��.yml����.yaml
//7.2yml�����ļ����﷨
A.������ͨ���� 
�﷨�� key: value ʾ�����룺
 name: haoha
ע�⣺value֮ǰ��һ���ո�
B.���ö������� 
�﷨��
  key:
  key1: value1
  key2: value2
  ���ߣ�
  key: {key1: value1,key2: value2} ʾ�����룺
 person:  name: haohao  age: 31  addr: beijing
#����
person: {name: haohao,age: 31,addr: beijing}
ע�⣺key1ǰ��Ŀո�������޶�����yml�﷨�У���ͬ��������ͬһ������
C. ����Map���� 
ͬ����Ķ���д��
D.�������飨List��Set������ 
�﷨��
  key:
  - value1
  - value2 ���ߣ�
  key: [value1,value2] 
city:  - beijing  - tianjin  - shanghai  - chongqing  #���� 
city: [beijing,tianjin,shanghai,chongqing]
#�����е�Ԫ���Ƕ�����ʽ 
student:  - name: zhangsan    age: 18    score: 100  - name: lisi    age: 28    score: 88  - name: wangwu    age: 38    score: 90
ע�⣺value1��֮��� - ֮�����һ���ո� 
ע�����
1.����������ʹ��tabֻ��ʹ�ÿո�
2.���ݸ�ʽΪ������:(�ո�)ֵ
3.�ո�ĸ�������Ҫ��ֻҪ��ͬ�㼶��Ԫ������뼴��
4.ע���á�#����
5.��Сд����
6.��ͬ�ϼ��Ĳ���һ������д����Ҫ��дһ�С�
