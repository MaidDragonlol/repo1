//@Service用于标注业务层组件
//
//@Controller用于标注控制层组件（如struts中的action）
//
//@Repository用于标注数据访问组件，即DAO组件
//
//@Component泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注。


package com.example.demo3.Controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        TextEditor te = (TextEditor) context.getBean("textEditor");  //getBean是ApplicationContext中的方法，用于获取注入对象
        te.spellCheck();
        TextEditor te1 = (TextEditor) context.getBean("textEditor");
        te1.spellCheck();

    }
}
