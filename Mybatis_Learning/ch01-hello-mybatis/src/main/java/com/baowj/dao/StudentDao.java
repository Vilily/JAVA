package com.baowj.dao;

import com.baowj.domain.Student;

import java.util.List;

// 操作Student表的接口
public interface StudentDao {

    public List<Student> selectStudents();

    // 插入方法
    public int insertStudent(Student student);

}
