/**
 * @author Bao WJ
 * @date 2021/11/25 16:40
 */
package com.baowj;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class TestForkJoin extends RecursiveTask<Long> {

    private long start;
    private long end;

    private long tmp = 10000L;

    public TestForkJoin(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> task = new TestForkJoin(1, 10_0000_0000L);
        ForkJoinTask<Long> submit = forkJoinPool.submit(task);
        Long sum = submit.get();

        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    @Override
    protected Long compute() {
        if ((end - start) < tmp) {
            Long sum = 0L;
            for (Long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            long mid = (start + end) / 2;
            TestForkJoin task1 = new TestForkJoin(start, mid);
            task1.fork(); // 将线程压入队列
            TestForkJoin task2 = new TestForkJoin(mid + 1, end);
            task2.fork();
            return task1.join() + task2.join();
        }
    }
}
