package com.baowj.service.impl;

import com.baowj.dao.StudentDao;
import com.baowj.entity.Student;
import com.baowj.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    // 引用类型
    private StudentDao studentDao;


    // 使用set注入，赋值
    public void setStudentDao(StudentDao studentDao){
        this.studentDao =  studentDao;
    }


    @Override
    public int addStudent(Student student) {
        return studentDao.insertStudent(student);
    }

    @Override
    public List<Student> queryStudents() {
        List<Student> students = studentDao.selectStudents();
        return students;
    }
}
