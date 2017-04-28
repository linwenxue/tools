package com.lin.study.java.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wenxuelin on 2017/4/28.
 *
 * 使用了 ReentrantLock 的 lock 和 unlock 来提现一般锁的特性，确保线程的有序执行。
 * 此种场景 synchronized 也适用。
 */
public class ReentrantLockTest1 {
    private static class Counter {

        private ReentrantLock mReentrantLock = new ReentrantLock();

        public void count() {
            mReentrantLock.lock();
            try {
                for (int i = 0; i < 6; i++) {
                    System.out.println(Thread.currentThread().getName() + "， i = " + i);
                }
            } finally {
                // 必须在 finally 释放锁
                mReentrantLock.unlock();
            }
        }
    }

    private static class MyThread extends Thread {

        private Counter mCounter;

        public MyThread(Counter counter) {
            mCounter = counter;
        }

        @Override
        public void run() {
            super.run();
            mCounter.count();
        }
    }

    public static void main(String[] var0) {
        Counter counter = new Counter();
        // 注：myThread1 和 myThread2 是调用同一个对象 counter
        MyThread myThread1 = new MyThread(counter);
        MyThread myThread2 = new MyThread(counter);
        myThread1.start();
        myThread2.start();
    }
}
