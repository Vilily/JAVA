package com.baowj.study02;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.Date;

// 表示当前类是切面类
@Aspect
public class MyAspect {
    /**
     * 后置通知
     * 1. 公共方法public
     * 2. 方法没有返回值
     * 3. 方法名称自定义
     * 4. 方法有参数（推荐Object），参数名自定义
     */

    @AfterReturning(value = "execution(* *..SomeServiceImpl.doOther(..))", returning = "res")
    public void myAfterReturning(Object res){
        System.out.println("后置通知："+res);
    }
}
