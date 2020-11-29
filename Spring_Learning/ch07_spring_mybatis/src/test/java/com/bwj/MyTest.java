package com.bwj;
import com.bwj.dao.StudentDao;
import com.bwj.entity.Student;
import com.bwj.service.StudentService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MyTest {

    @Test
    public void test01()
    {
        String config = "applicationContext.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
        String names[] = ctx.getBeanDefinitionNames();
        for (String na:names){
            System.out.println("容器中对象名称"+na);
        }
    }

    @Test
    public void test02(){
        String config = "applicationContext.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
        // 获取spring容器中的dao对象
        StudentDao dao = (StudentDao) ctx.getBean("studentDao");
        Student student = new Student();
        student.setId(1012);
        student.setName("张三");
        student.setEmail("zhangsahn@qq.com");
        student.setAge(20);
        System.out.println("student"+student);
        System.out.println("dao:"+dao);
//        dao.selectStudents();
        int num = dao.insertStudent(student);
        System.out.println("nums=" + num);
    }

    @Test
    public void testServiceSelect(){
        String config = "applicationContext.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
        StudentService service = (StudentService)ctx.getBean("studentService");
        List<Student> students=service.queryStudents();
        for(Student stu:students){
            System.out.println(stu);
        }
    }
}
