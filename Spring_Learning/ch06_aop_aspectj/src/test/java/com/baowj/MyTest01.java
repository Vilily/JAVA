package com.baowj;

import com.baowj.study01.SomeService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest01 {
    @Test
    public void test01(){
        String config = "applicationContext.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
        SomeService proxy = (SomeService)ctx.getBean("someService");

        proxy.doSome("李四",20);
    }
}
