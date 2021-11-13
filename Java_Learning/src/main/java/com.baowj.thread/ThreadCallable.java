/**
 * @author Bao Wenjie
 */
package com.baowj.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class ThreadCall implements Callable{
    @Override
    public Object call() throws Exception {
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
                sum += i;
            }
        }
        return sum;
    }
}

class ThreadCallTest {
    public static void main(String[] args) {
        ThreadCall threadCall = new ThreadCall();

        FutureTask futureTask = new FutureTask(threadCall);

        new Thread(futureTask).start();

        try {
            Object sum = futureTask.get();
            System.out.println(sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}