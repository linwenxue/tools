package com.lin.java.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wenxuelin on 2017/4/28.
 *
 * 线程 thread2 等待 300ms 后 timeout，中断等待成功。
 */
public class ReentrantLockTest5 {
    static final int TIMEOUT = 3000;

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
                //tryLock(long timeout, TimeUnit unit) 尝试获得锁
                boolean isLock = mReentrantLock.tryLock(300, TimeUnit.MILLISECONDS);
                System.out.println(Thread.currentThread().getName() + " isLock:" + isLock);
                if(isLock){
                    for (int i = 0; i < 6; i++) {
                        // 模拟耗时，突出线程是否阻塞
                        long startTime = System.currentTimeMillis();
                        while (true) {
                            if (System.currentTimeMillis() - startTime > 100)
                                break;
                        }
                        System.out.println(Thread.currentThread().getName() + " doOtherThing， i = " + i);
                    }
                }else{
                    System.out.println(Thread.currentThread().getName() + " timeout");
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
    }
}
