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
    public void testSelectMultiPosition(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        List<Student> students = dao.selectMultiPosition("李四", 23);
        for(Student s:students)
            System.out.println(s);
    }

    @Test
    public void testSelectMultiObject(){
        QueryParam queryParam = new QueryParam();
        queryParam.setParamName("李四");
        queryParam.setParamAge(23);
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        List<Student> students = dao.selectMultiObject(queryParam);
        for(Student s:students)
            System.out.println(s);
    }

    @Test
    public void testSelectMultiMap(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        Map<String, Object> map = new HashMap<>();
        map.put("name", "李四");
        map.put("age", 23);
        List<Student> students = dao.selectMultiMap(map);
        for(Student s:students)
            System.out.println(s);
    }
}
