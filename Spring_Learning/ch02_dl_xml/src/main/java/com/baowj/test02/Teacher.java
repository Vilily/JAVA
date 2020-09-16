package com.baowj.test02;

public class Teacher {
    private String name;
    private int age;

    private School school;

    /**
     * Teacher的有参构造方法
     * @param myname
     * @param myage
     * @param mySchool
     */
    public Teacher(String myname, int myage, School mySchool){
        System.out.println("====Teacher有参数构造方法====");
        this.name = myname;
        this.age = myage;
        this.school = mySchool;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", school=" + school +
                '}';
    }
}
