package com.lin.java.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程下volatile也不能保证变量的同步
 * Created by linwenxue on 2015/3/19.
 */
public class VolatileTest {
    //线程安全
    public static volatile AtomicInteger race = new AtomicInteger(0);
    //线程不安全
    public static volatile int race_int = 0;

    //将该方法添加synchroized即可实现多线程安全访问
    public static void increase() {
        //线程安全
        //race.addAndGet(1);
        //线程不安全
        race_int++;
    }

    private static final int THREADS_COUNT = 20;

    public static void main(String[] args) {
        //很奇怪，直接使用jdk编译或eclipse编译运行，输出为“1”，使用idea编译运行，输出为“2”，why?
        System.out.println(Thread.activeCount());
        //打印线程组的所有线程
        Thread.currentThread().getThreadGroup().list();

        Thread[] threads = new Thread[THREADS_COUNT];
        for(int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        increase();
                    }
                }
            });
            threads[i].start();
        }

        /*
        等待所有累加线程都结束Thread.activeCount() > 1永远为true;
        Thread.activeCount() > 2则为正确 why?
        以为可能是idea启动一个监控线程，如下
        java.lang.ThreadGroup[name=main,maxpri=10]
        Thread[main,5,main]
        Thread[Monitor Ctrl-Break,5,main]
         */
        while(Thread.activeCount() > 2) {
            System.out.println(Thread.currentThread().getName());
            Thread.yield();
        }

        System.out.println(race_int);
    }
}
