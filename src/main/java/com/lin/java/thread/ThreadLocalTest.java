package com.lin.java.thread;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by linwenxue on 2015/2/4.
 * Desc:
 * static修饰普通的成员变量，所有的实例共享一个static变量
 * ThreadLocal模式则每个线程维护变量的一份副本
 */
public class ThreadLocalTest implements Runnable{
    public static Double a = Math.random();
    private static ThreadLocal<Date> startDate =
        new ThreadLocal<Date>() {
            protected Date initialValue() {
                return new Date();
            }
        };
    private static final ThreadLocal<Double> threadId =
        new ThreadLocal<Double>() {
            @Override
            protected Double initialValue() {
                return Math.random();
            }
        };

    public Double getThreadId() {
        return threadId.get();
    }


    public static void main(String[] args) {
        ThreadLocalTest t1 = new ThreadLocalTest();
        ThreadLocalTest t2 = new ThreadLocalTest();
        System.out.println(t1.a);
        System.out.println(t2.a);
        System.out.println("=============================================================");
        Thread t3 = new Thread(new ThreadLocalTest());
        t3.start();
        Thread t4 = new Thread(new ThreadLocalTest());
        t4.start();

    }

    @Override
    public void run() {
        System.out.printf("Starting Thread: %s : %s\n",
                getThreadId(), startDate.get());
        System.out.println(a);
        try {
            TimeUnit.SECONDS.sleep((int) Math.rint(Math.random() * 10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Thread Finished: %s : %s\n",
                getThreadId(), startDate.get());
    }
}
