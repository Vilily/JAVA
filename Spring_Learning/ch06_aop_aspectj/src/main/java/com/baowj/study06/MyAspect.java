package com.baowj.study06;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

// 表示当前类是切面类
@Aspect
public class MyAspect {
    /**
     * 最终通知方法的定义
     * 1. public
     * 2. 没有返回值
     * 3. 方法名称自定义
     * 4. 方法没有参数，可以有JoinPoint
     */

    @After(value = "mypt()")
    private void myAfter(){
        System.out.println("最终通知");
    }

    @Before(value = "mypt()")
    public void myBefore(){
        System.out.println("前置通知");
    }
    @Pointcut(value = "execution(* *..SomeServiceImpl.doThird(..))")
    public void mypt(){
        //无需代码
    }
}
