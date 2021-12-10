/**
 * @author Bao Wenjie
 * @date 2021/11/14
 */

package com.baowj;

import java.util.concurrent.CountDownLatch;

public class JUCCountDownLatch {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + " Go out");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println("Close Door");
    }
}