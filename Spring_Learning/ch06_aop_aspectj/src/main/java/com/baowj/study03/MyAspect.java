package com.baowj.study03;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.Date;

// 表示当前类是切面类
@Aspect
public class MyAspect {
    /**
     * 环绕通知方法的定义
     * 1. public
     * 2. 必须有一个返回值，推荐使用Object
     * 3. 方法名称自定义
     * 4. 方法有固定参数proceedingJoinPoint
     *
     */

    /***
     * @Around: 环绕通知
     *  属性：value 切入点表达式
     *
     */
    @Around(value = "execution(* *..SomeServiceImpl.doFirst(..))")
    public Object myAround(ProceedingJoinPoint pjp) throws Throwable {
        Object result = null;
        System.out.println("环绕通知：在目标方法之前，时间" + new Date());
        //1. 目标方法调用
        // 获取第一个参数值
        String name = "";
        Object args[] = pjp.getArgs();
        if(args!= null && args.length >= 1){
            Object arg = args[0];
            name = (String)arg;
        }
        if("李四".equals(name)){
            // 如果符合条件，调用目标方法
            result = pjp.proceed();
        }
        result = pjp.proceed(); //等同于method.invoke()
        System.out.println("环绕通知：在目标方法之后，提交事物");
        //2. 在目标方法前后加功能

        // 修改目标方法的执行结果
        if(result != null){
            result = "Hello AspectJ AOP";
        }
        //返回目标方法的执行结果
        return result;
    }
}
