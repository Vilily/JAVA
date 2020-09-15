package com.baowj.test02;

public class Student {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString(){
        return "Student{" +
                "name='" +
                this.name +
                "\'" +
                ",age=" +
                this.age +
                "}";
    }
}
