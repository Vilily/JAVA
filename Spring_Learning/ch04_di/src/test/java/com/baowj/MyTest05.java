package com.baowj;

import com.baowj.study05.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest05 {

    @Test
    public void test05(){
        String config = "applicationContext_05.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);

        Student student =  (Student) ac.getBean("myStudent");
        System.out.println(student);
    }
}
