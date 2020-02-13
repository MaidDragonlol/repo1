package com.example.demo3.Controller;

import org.springframework.beans.factory.annotation.Autowired;

public class TextEditor {
    @Autowired
    /*<!--其实在启动spring IoC时，容器自动装载了一个AutowiredAnnotationBeanPostProcessor后置处理器，
    当容器扫描到@Autowied、@Resource或@Inject时，就会在IoC容器自动查找需要的bean，并装配给该对象的属性-->
<!--具体流程为：在使用@Autowired时，首先在容器中查询对应类型的bean
    　　　　如果查询结果刚好为一个，就将该bean装配给@Autowired指定的数据
    　　　　如果查询的结果不止一个，那么@Autowired会根据名称来查找。
            　　　　如果查询的结果为空，那么会抛出异常。解决方法时，使用required=false-->
    <!-- Definition for textEditor bean without constructor-arg  -->*/
    private SpellChecker spellChecker;

    public void setSpellChecker( SpellChecker spellChecker ){
        this.spellChecker = spellChecker;
    }
    public void spellCheck() {
        spellChecker.checkSpelling();
    }

}
