package com.baowj;

import com.baowj.test02.School;
import com.baowj.test02.Student;
import com.baowj.test02.Teacher;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

//    @Test
//    public void test01(){
//        String config = "applicationContext.xml";
//        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
//
//        // 从容器中获取Student对象
//        Student mystudent = (Student) ac.getBean("myStudent");
//        System.out.println(mystudent);
//    }

    @Test
    public void test02(){
        System.out.println("====test02====");
        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        Student myStudent = (Student)ac.getBean("myStudent");
        System.out.println(myStudent);
    }

    @Test
    public void test03() {
        System.out.println("====test03====");
        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        Teacher myTeacher = (Teacher) ac.getBean("myTeacher");
        System.out.println(myTeacher);
    }

    @Test
    public void test04() {
        System.out.println("====test04====");
        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        Teacher myTeacher = (Teacher) ac.getBean("myTeacher1");
        System.out.println(myTeacher);
    }

    @Test
    public void test05() {
        System.out.println("====test05====");
        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        Student myStudent = (Student) ac.getBean("myStudent1");
        System.out.println(myStudent);
    }
}
