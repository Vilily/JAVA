package com.baowj;

import com.baowj.dao.StudentDao;
import com.baowj.domain.Student;
import com.baowj.uils.MyBatisUtils;
import com.baowj.vo.QueryParam;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.awt.geom.QuadCurve2D;
import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMyBatis {
    @Test
    public void testSelectStudentIf() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        Student student = new Student();
        student.setName("李四");
        student.setAge(1);
        List<Student> students = dao.selectStudentIf(student);
        for(Student stu:students)
        {
            System.out.println(stu);
        }
    }



    @Test
    public void testSelectStudentWhere() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        Student student = new Student();
//        student.setName("李四");
//        student.setAge(1);
        List<Student> students = dao.selectStudentWhere(student);
        for(Student stu:students)
        {
            System.out.println(stu);
        }
    }
    @Test
    public void testSelectStudentForeach() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        Student student = new Student();
//        student.setName("李四");
//        student.setAge(1);

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        List<Student> students = dao.selectForeachOne(list);
        for(Student stu:students)
        {
            System.out.println(stu);
        }
    }
}
