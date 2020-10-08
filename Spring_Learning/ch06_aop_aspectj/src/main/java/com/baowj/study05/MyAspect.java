package com.baowj.study05;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

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

    /**
     *
     */
    @After(value = "execution(* *..SomeServiceImpl.doThird(..))")
    public void myAfter(){
        System.out.println("最终通知");
    }
}
