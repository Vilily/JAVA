package com.baowj.study01;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.Date;

// 表示当前类是切面类
@Aspect
public class MyAspect {
    /**
     * 定义实现切面功能的方法：
     * 要求：
     * 1. 公共方法 public
     * 2. 方法没有返回值
     * 3. 方法名称自定义
     * 4. 方法可以有参数也可以没有
     *
     */

    /**
     * @Before 前置通知
     * 属性value：切入点表达式，表示切面的功能执行位置
     * 位置：方法上面
     * 特点：
     * 1. 在目标方法之前执行
     * 2. 不会改变目标方法的执行结果
     * 3. 不会影响目标方法的执行
     */
    @Before(value = "execution(public void com.baowj.study01.SomeServiceImpl.doSome(String, Integer))")
    public void myBefore(){
        // 功能
        System.out.println("切面功能：目标方法前输出时间"+ new Date());
    }
}
