package com.baowj.study05;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Resource
 * 来自jdk的注解，可以给引用类型赋值
 */

@Component(value = "myStudent")
public class Student {

    @Value("李四")
    private String name;
    private Integer age;

    @Resource
    private School mySchool;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", mySchool=" + mySchool +
                '}';
    }
}
