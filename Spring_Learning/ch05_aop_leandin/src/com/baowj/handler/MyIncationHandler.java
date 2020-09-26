package com.baowj.handler;

import com.baowj.util.ServiceTools;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyIncationHandler implements InvocationHandler {

    // 目标对象
    private Object target;

    public MyIncationHandler(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 通过代理对象执行方法时，会调用执行invoke
        Object results = null;
        ServiceTools.doLog();
        // 执行目标类的方法，通过Method实现
        results = method.invoke(target, args);
        ServiceTools.doTrans();
        // 返回执行结果
        return results;
    }
}
