package com.baowj;

import com.baowj.dao.StudentDao;
import com.baowj.domain.Student;
import com.baowj.uils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import java.util.List;

public class TestMyBatis {
    @Test
    public void testSelectStudents(){
        /**
         * 使用mybatis动态代理
         */
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        List<Student> students = dao.selectStudents();
        for(Student student:students)
        {
            System.out.println(student);
        }
    }

    @Test
    public void testInsertStudent(){
    }
}
