package com.lin.study.java.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wenxuelin on 2017/4/28.
 *
 * 线程 thread2 等待 300ms 后 timeout，中断等待成功。
 */
public class ReentrantLockTest4 {
    static final int TIMEOUT = 300;

    private static class Counter {

        private ReentrantLock mReentrantLock = new ReentrantLock();

        public void count() {
            try{
                //lock() 不可中断
                mReentrantLock.lock();
                // 模拟耗时，突出线程是否阻塞
                for (int i = 0; i < 6; i++) {
                    long startTime = System.currentTimeMillis();
                    while (true) {
                        if (System.currentTimeMillis() - startTime > 100)
                            break;
                    }
                    System.out.println(Thread.currentThread().getName() + "， i = " + i);
                }
            } finally {
                // 必须在 finally 释放锁
                mReentrantLock.unlock();
            }
        }

        public void doOtherThing(){
            try{
                //lockInterruptibly() 可中断，若线程没有中断，则获取锁
                mReentrantLock.lockInterruptibly();
                for (int i = 0; i < 6; i++) {
                    // 模拟耗时，突出线程是否阻塞
                    long startTime = System.currentTimeMillis();
                    while (true) {
                        if (System.currentTimeMillis() - startTime > 100)
                            break;
                    }
                    System.out.println(Thread.currentThread().getName() + " doOtherThing， i = " + i);
                }
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " 中断 ");
            }finally {
                // 若当前线程持有锁，则释放
                if(mReentrantLock.isHeldByCurrentThread()){
                    mReentrantLock.unlock();
                }
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
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                counter.doOtherThing();
            }
        });
        thread2.start();
        long start = System.currentTimeMillis();
        while (true){
            if (System.currentTimeMillis() - start > TIMEOUT) {
                // 若线程还在运行，尝试中断
                if(thread2.isAlive()){
                    System.out.println(" 不等了，尝试中断 ");
                    thread2.interrupt();
                }
                break;
            }
        }
    }
}
