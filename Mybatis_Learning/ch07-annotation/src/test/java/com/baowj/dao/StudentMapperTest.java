/**
 * @author Bao WJ
 * @date 2021/12/9 11:02
 */
package com.baowj.dao;

import com.baowj.domain.Student;
import com.baowj.uils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class StudentMapperTest {

    @Test
    public void testGetStudents() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.getStudents();
        for (Student student : students) {
            System.out.println(student);
        }
        sqlSession.close();
    }

    @Test
    public void testGetUserByID() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student student = mapper.getUserByID(536);
        System.out.println(student);
        sqlSession.close();
    }

    @Test
    public void testAddStudent() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        mapper.addStudent(new Student(536, "gdehd", "uytwegd@qq.com", 90));
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testUpdateStudentName() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        mapper.updateStudentName(536, "baowj");
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testDeleteStudent() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        mapper.deleteStudent(536);
        sqlSession.commit();
        sqlSession.close();
    }
}
