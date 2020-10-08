package com.baowj.study03;

// 目标类
public class SomeServiceImpl implements SomeService {
    @Override
    public void doSome(String name, Integer age) {
        // 给doSome方法增加功能
        System.out.println("====目标方法doSome====");
    }

    @Override
    public String doOther(String name, Integer age) {
        System.out.println("====目标方法doOther()====");
        return "abcd";
    }

    @Override
    public String doFirst(String name, Integer age) {
        System.out.println("======业务方法doFirst======");
        return "doFirst";
    }
}
