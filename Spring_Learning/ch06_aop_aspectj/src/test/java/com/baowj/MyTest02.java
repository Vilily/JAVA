package com.baowj;

import com.baowj.study02.SomeService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest02 {
    @Test
    public void test02(){
        String config = "applicationContext.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
        SomeService proxy = (SomeService)ctx.getBean("someService");

        String str = proxy.doOther("李四",20);

    }
}
