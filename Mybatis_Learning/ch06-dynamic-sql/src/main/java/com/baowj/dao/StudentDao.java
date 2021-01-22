package com.baowj.dao;

import com.baowj.domain.Student;

import java.util.List;

public interface StudentDao {
    List<Student> selectStudentIf(Student student);

    List<Student> selectStudentWhere(Student student);

    List<Student> selectForeachOne(List<Integer> list);
}
