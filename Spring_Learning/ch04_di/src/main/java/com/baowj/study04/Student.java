package com.baowj.study04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Component: 创建对象的，等于<bean>的功能
 *  属性：value 对象名称，也就是bean的id值
 *       value值是唯一的，在整个spring容器只有一个
 *  位置：在类的上面
 *      @Component(value = "myStudent") 等同于
 *      <bean id="myStudent" calss="com.baowj.study01.Student" />
 */

@Component(value = "myStudent")
public class Student {

    @Value("李四")
    private String name;
    private Integer age;

    @Autowired
    @Qualifier(value = "mySchool")
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
