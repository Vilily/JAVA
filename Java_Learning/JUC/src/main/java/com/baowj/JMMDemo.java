/**
 * @author Bao WJ
 * @date 2021/11/26 11:17
 */
package com.baowj;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class JMMDemo {
    private volatile static AtomicInteger num = new AtomicInteger(0);

    public static void add() {
        num.getAndIncrement();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    JMMDemo.add();
                }
            }).start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(num);
    }
}
