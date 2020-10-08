package com.baowj.study04;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.Date;

// 表示当前类是切面类
@Aspect
public class MyAspect {
    /**
     * 异常通知方法的定义
     * 1. public
     * 2. 没有返回值
     * 3. 方法名称自定义
     * 4. 方法可以没有参数，可以有JoinPoint
     */

    /**
     * @AfterThrowing: 异常通知
     */
    @AfterThrowing(value = "execution(* *..SomeServiceImpl.doSecond(..))", throwing = "ex")
    public void myAfterThrowing(Exception ex){
        System.out.println("异常通知，执行");
    }

}
