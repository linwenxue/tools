package com.lin.java.concurrent.lock;

/**
 * java对象锁只对添加锁的方法起作用，同时访问未加锁的方法不存在互斥
 */
public class ObjectLockTest {
    public void test1() {
        System.out.println("test1 started");
        System.out.println("1");
        System.out.println("test1 end");
    }

    public synchronized void test2() throws Exception{
        System.out.println("test2 started");
        System.out.println("2");
        Thread.sleep(10000);
        System.out.println("test2 end");
    }

    public void test3() throws Exception{
        System.out.println("test3 started");
        synchronized (this) {
            System.out.println("3");
            Thread.sleep(10000);
        }
        System.out.println("test3 end");
    }

    public void test4() {
        System.out.println("test4 started");
        System.out.println("4");
        System.out.println("test4 end");
    }

    public static void main(String[] args) throws Exception{
        ObjectLockTest t = new ObjectLockTest();

        Thread r1 = new Thread(){
            @Override
            public void run() {
                t.test1();
            }
        };

        Thread r2 = new Thread(){
            @Override
            public void run() {
                try {
                    t.test2();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Thread r3 = new Thread(){
            @Override
            public void run() {
                try {
                    t.test3();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Thread r4 = new Thread(){
            @Override
            public void run() {
                t.test4();
            }
        };

        r2.start();
        r3.start();
        Thread.sleep(3000);
        r1.start();
        r4.start();
    }
}
