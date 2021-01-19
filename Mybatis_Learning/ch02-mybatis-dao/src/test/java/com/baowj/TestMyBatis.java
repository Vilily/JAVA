package com.baowj;

import com.baowj.dao.StudentDao;
import com.baowj.dao.impl.StudentImpl;
import com.baowj.domain.Student;
import org.junit.Test;

import java.util.List;

public class TestMyBatis {
    @Test
    public void testSelectStudents(){
        StudentDao dao = new StudentImpl();
        List<Student> students = dao.selectStudents();
        for(Student student:students)
        {
            System.out.println(student);
        }
    }

    @Test
    public void testInsertStudent(){
        StudentDao dao = new StudentImpl();
        Student student = new Student();
        student.setAge(78);
        student.setId(9);
        student.setName("haoah");
        student.setEmail("jhebvgdh.mm");
        int res = dao.insertStudent(student);
        System.out.println(res);
    }
}
