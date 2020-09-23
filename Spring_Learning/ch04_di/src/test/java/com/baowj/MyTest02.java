package com.baowj;

import com.baowj.study02.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest02 {

    @Test
    public void test02(){
        String config = "applicationContext_02.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);

        Student student =  (Student) ac.getBean("myStudent");
        System.out.println(student);
    }
}
