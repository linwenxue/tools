package com.lin.study.java.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wenxuelin on 2017/4/28.
 */
public class ReentrantLockTest2 {
    private static class Counter {

        private ReentrantLock mReentrantLock = new ReentrantLock();

        public void count() {
            for (int i = 0; i < 6; i++) {
                mReentrantLock.lock();
                // 模拟耗时，突出线程是否阻塞
                try{
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName() + "， i = " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 必须在 finally 释放锁
                    mReentrantLock.unlock();
                }
            }
        }

        public void doOtherThing(){
            for (int i = 0; i < 6; i++) {
                // 模拟耗时，突出线程是否阻塞
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " doOtherThing， i = " + i);
            }
        }
    }

    public static void main(String[] var0) {
        final Counter counter = new Counter();
        new Thread(new Runnable() {
            @Override
            public void run() {
                counter.count();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                counter.doOtherThing();
            }
        }).start();
    }
}
