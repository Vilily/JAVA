package com.baowj;

import com.baowj.domain.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMyBatis {

    @Test
    public void testInsert() throws IOException {
        // 1. 定义mybatis主配置文件名称路径
        String config = "mybatis.xml";
        // 2. 读取这个config文件
        InputStream in = Resources.getResourceAsStream(config);
        // 3. 创建SqlSessionFactoryBuilder
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        // 4. 创建SqlSessionFactory
        SqlSessionFactory factory = builder.build(in);
        // 5. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 6. 指定要执行sql语句标识
        String sqlId = "com.baowj.dao.StudentDao" + "." + "insertStudent";
        // 7. 执行sql语句
        Student student = new Student();
        student.setId(1005);
        student.setAge(23);
        student.setEmail("liubei@gamil.com");
        student.setName("刘备");
        int tmp = sqlSession.insert(sqlId, student);
        // mybatis默认不提交事物
        // 8. 提交事物
        sqlSession.commit();
        // 9. 输出结果
        System.out.println(tmp);
        // 10. 关闭SqlSession对象
        sqlSession.close();
    }
}
