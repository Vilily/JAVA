/**
 * @author Bao Wenjie
 * @email bwj_678@qq.com
 * @date 2021/11/7
 */
package com.baowj.thread;

import java.util.concurrent.locks.ReentrantLock;

class LockTicket implements Runnable {

    private int ticket = 100;

    private ReentrantLock lock = new ReentrantLock();
    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                if (ticket > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ":" + ticket);
                    ticket--;
                } else {
                    break;
                }
            } finally {
                lock.unlock();
            }

        }
    }
}

class LockTest {
    public static void main(String[] args) {
        LockTicket lockTicket = new LockTicket();

        Thread thread1 = new Thread(lockTicket);
        Thread thread2 = new Thread(lockTicket);
        Thread thread3 = new Thread(lockTicket);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}

