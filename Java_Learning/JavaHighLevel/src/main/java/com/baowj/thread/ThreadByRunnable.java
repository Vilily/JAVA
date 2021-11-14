package com.baowj.thread;

import org.junit.Test;

/**
 * @author Bao Wenjie
 * 利用Runnable接口实现多线程
 */

class SyncThread implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}

class MyTest {
    public static void main(String[] args) {
        SyncThread syncThread = new SyncThread();
        Thread thread1 = new Thread(syncThread);
        thread1.setName("线程一");
        thread1.start();

        Thread thread2 = new Thread(syncThread);
        thread2.setName("线程二");
        thread2.start();
    }
}
