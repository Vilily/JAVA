package com.baowj.thread;


class MyThreadMethod implements Runnable {
    Object object = new Object();
    private static int ticket = 100;

    @Override
    public void run() {
        while (true) {
            if (!getTicket()) {
                break;
            }
        }
    }

    public synchronized boolean getTicket () {
        if (ticket > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " : " + ticket);
            ticket--;
            return true;
        } else {
            return false;
        }
    }
}

class Test1 {
    public static void main(String[] args) {
        MyThreadMethod myThread = new MyThreadMethod();
        Thread thread1 = new Thread(myThread);
        thread1.setName("线程1");
        thread1.start();

        Thread thread2 = new Thread(myThread);
        thread2.setName("线程2");
        thread2.start();
    }
}


