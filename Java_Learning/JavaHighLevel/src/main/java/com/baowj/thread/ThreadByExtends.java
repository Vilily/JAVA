/**
 * @author Bao Wenjie
 */
package com.baowj.thread;

class MyThreadExtends extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }
}

class ThreadTest {
    public static void main(String[] args) {
        MyThreadExtends myThreadExtends1 = new MyThreadExtends();
        myThreadExtends1.setName("线程0");
        myThreadExtends1.start();
        MyThreadExtends myThreadExtends2 = new MyThreadExtends();
        myThreadExtends2.setName("线程1");
        myThreadExtends2.start();
    }
}