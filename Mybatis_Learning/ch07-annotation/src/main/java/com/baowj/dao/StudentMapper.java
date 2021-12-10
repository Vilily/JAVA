package com.baowj.dao;

import com.baowj.domain.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface StudentMapper {

    @Select("select * from student")
    List<Student> getStudents();

    @Select("select * from student where id = #{id}")
    Student getUserByID(@Param("id") int id);

    @Insert("insert into student(id, name, email, age) values (#{id}, #{name}, #{email}, #{age})")
    int addStudent(Student student);

    @Update("update student set name=#{name} where id=#{id}")
    int updateStudentName(@Param("id") Integer id, @Param("name") String name);

    @Delete("delete from student where id=#{id}")
    int deleteStudent(@Param("id") int id);
}
