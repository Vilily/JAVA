package com.baowj;

import com.baowj.dao.StudentDao;
import com.baowj.domain.Student;
import com.baowj.uils.MyBatisUtils;
import com.baowj.vo.QueryParam;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.awt.geom.QuadCurve2D;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMyBatis {
    @Test
    public void testSelectStudents(){
        /**
         * 使用mybatis动态代理
         */
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        Student students = dao.selectStudentById(1);
        System.out.println(students);
    }

    @Test
    public void testSelectMultiParam(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        List<Student> students = dao.selectMultiParam("李四", 23);
        for(Student s:students)
            System.out.println(s);
    }

    @Test
    public void testSelectCount(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        System.out.println(dao.selectCount());
    }

    @Test
    public void testSelectMap(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        Map<Object, Object> map = dao.selectMapById(1);
        System.out.println(map);
    }


    @Test
    public void testSelectAllStudents(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        List<Student> students = dao.selectAllStudents();
        for(Student stu: students)
        {
            System.out.println(stu);
        }
    }

}
