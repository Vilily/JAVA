package com.baowj.study02;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void test01(){
        // 加载总配置文件
        String config = "spring-total.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);

        // 从容器中获取Student对象
        Student student = (Student)ac.getBean("myStudent");
        System.out.println(student);

    }
}
