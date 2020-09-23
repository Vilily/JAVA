package com.baowj;

import com.baowj.study03.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest03 {

    @Test
    public void test03(){
        String config = "applicationContext_03.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);

        Student student =  (Student) ac.getBean("myStudent");
        System.out.println(student);
    }
}
