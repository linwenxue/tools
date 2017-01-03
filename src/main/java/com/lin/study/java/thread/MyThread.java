package com.lin.study.java.thread;

/**
 * Created by linwenxue on 2015/1/7.
 */
public class MyThread implements Runnable{
    private int count = 5;

    @Override
    public void run() {
        for (int i=0; i<10; ++i) {
            synchronized (this) {//添加同步
                if (this.count > 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+ "当前计数："+this.count--);
                }
            }
        }
    }

    public static void main(String [] args) {
        while(true) {
            MyThread my = new MyThread();
            new Thread(my, "线程1").start();
            new Thread(my, "线程2").start();
            new Thread(my, "线程3").start();
            if(my.count < 0) {
                System.out.println("出现负数");
                break;
            }
        }

    }
}
