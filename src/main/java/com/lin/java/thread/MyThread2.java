package com.lin.java.thread;

/**
 * Created by linwenxue on 2015/1/7.
 */
public class MyThread2 extends Thread {
    private int count = 5;

    @Override
    public void run() {
        for (int i=0; i<=10; i++) {
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

    public static void main(String [] args) {
        MyThread2 my = new MyThread2();
        new Thread(my, "线程1").start();
        new Thread(my, "线程2").start();
        new Thread(my, "线程3").start();
    }

}
