/**
 * @author Bao Wenjie
 * @date 2021/11/14
 */

package com.baowj.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


@Target(value = ElementType.METHOD)
public @interface MyAnnotation {
    String value() default "Hello";
}

class Person {
    private String name;
    private  int age;

    @MyAnnotation
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
