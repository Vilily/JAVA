package com.baowj;

import com.baowj.study06.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest06 {
    @Test
    public void test06(){
        String config = "applicationContext_06.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);

        Student student =  (Student) ac.getBean("myStudent");
        System.out.println(student);
    }
}
