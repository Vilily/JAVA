package com.baowj.dao;

import com.baowj.domain.Student;

import java.util.List;

public interface StudentDao {
    List<Student> selectStudents();


    int insertStudent(Student student);
}
