package com.baowj;

import com.baowj.study04.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest04 {

    @Test
    public void test04(){
        String config = "applicationContext_04.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);

        Student student =  (Student) ac.getBean("myStudent");
        System.out.println(student);
    }
}
