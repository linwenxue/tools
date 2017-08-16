package com.lin.java.concurrent.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wenxuelin on 2017/5/17.
 */
class Cat {
    public void test() {
        System.out.println("test.......");
        synchronized (this) {
            try {
                System.out.println("开始锁定");
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("结束锁定");
    }

    public  void test2() {
        System.out.println("test2.......");
    }
}


class CatTest1 implements Runnable{
    Cat cat;
    public CatTest1(Cat cat) {
        this.cat = cat;
    }

    @Override
    public void run() {
        cat.test();
    }
}


class CatTest2 implements Runnable{
    Cat cat;
    public CatTest2(Cat cat) {
        this.cat = cat;
    }

    @Override
    public void run() {
        cat.test2();
    }
}


public class SynchronizedTest {
    public static void main(String[] args) throws Exception{
        ExecutorService cachePool = Executors.newFixedThreadPool(2);
        Cat cat = new Cat();
        CatTest1 t1 = new CatTest1(cat);
        CatTest2 t2 = new CatTest2(cat);
        cachePool.execute(t1);
        Thread.sleep(1000);
        cachePool.execute(t2);
    }
}


