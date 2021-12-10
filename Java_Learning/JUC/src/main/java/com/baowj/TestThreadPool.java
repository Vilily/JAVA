/**
 * @author Bao WJ
 * @date 2021/11/24 16:18
 */
package com.baowj;

import java.util.concurrent.*;

public class TestThreadPool {
    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
        for (int i = 0; i < 10; i++) {
            threadPool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " OK");
            });
        }
        threadPool.shutdown();
    }
}
