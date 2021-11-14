/**
 * @author Bao Wenjie
 */
package com.baowj.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

class ThreadPoolNumber implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }
}

class ThreadPool {
    public static void main(String[] args) {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor)Executors.newFixedThreadPool(10);
        executorService.execute(new ThreadPoolNumber()); // Runnable
        executorService.execute(new ThreadPoolNumber()); // Runnable
        executorService.shutdown(); // 关闭线程池
    }
}