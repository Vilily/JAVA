package com.baowj;

import com.baowj.study03.SomeService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest03 {
    @Test
    public void test03(){
        String config = "applicationContext.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
        SomeService proxy = (SomeService)ctx.getBean("someService");

        String str = proxy.doFirst("李四",20);
        System.out.println(str);
    }
}
