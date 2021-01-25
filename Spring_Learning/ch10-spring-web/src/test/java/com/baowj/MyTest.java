package com.baowj;

import com.baowj.dao.StudentDao;
import com.baowj.entity.Student;
import com.baowj.service.StudentService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    @Test
    public void testService() {
        String config = "applicationContext.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
        System.out.println("容器:" + ctx);
        // 获取service
        StudentService studentService = (StudentService)ctx.getBean("studentService");
        StudentDao studentDao = (StudentDao) ctx.getBean("studentDao");
        Student student = new Student();
        student.setId(1999);
        student.setAge(22);
        student.setEmail("strEmail");
        student.setName("strName");
//        studentService.addStudent(student);
        studentDao.insertStudent(student);
    }
}
