package com.baowj.service.impl;

import com.baowj.service.SomeService;
import com.baowj.util.ServiceTools;

import java.util.Date;

public class SomeServiceImpl implements SomeService {
    @Override
    public void doSome() {
//        ServiceTools.doLog();
        System.out.println("执行业务方法doSome");
//        ServiceTools.doTrans();
    }

    @Override
    public void doOther() {
//        ServiceTools.doLog();
        System.out.println("执行业务方法doOther");
//        ServiceTools.doTrans();
    }
}
