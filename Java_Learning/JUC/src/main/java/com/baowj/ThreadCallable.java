/**
 * @author Bao Wenjie
 * @date 2021/11/14
 */

package com.baowj;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ThreadCallable {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        FutureTask futureTask = new FutureTask(myThread);
        FutureTask futureTask1 = new FutureTask(myThread);
        new Thread(futureTask, "A").start();
        new Thread(futureTask1, "B").start();

    }
}

class MyThread implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "-call()");
        return 9;
    }
}