package com.baowj;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    @org.junit.Test
    public void test01() {
        HelloWorldService service = new HelloWorldServiceImpl();
        service.helloWorld();
    }

    @org.junit.Test
    public void test02(){
        String config = "beans.xml";
        ApplicationContext hello = new ClassPathXmlApplicationContext(config);
        HelloWorldService tmp =  (HelloWorldService) hello.getBean("HelloWorld");
        tmp.helloWorld();
    }
}
