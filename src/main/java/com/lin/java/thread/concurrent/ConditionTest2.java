package com.lin.java.thread.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wenxuelin on 2017/7/20.
 */
public class ConditionTest2 {
    private Lock lock = new ReentrantLock();
    private Condition readCondition = lock.newCondition();
    private Condition writeCondition = lock.newCondition();
    private final static int MAX_MESSAGE = 100;
    private Object[] messages = new Object[MAX_MESSAGE];
    int readIndex, writeIndex,count;

    public void put(Object message) throws InterruptedException{
        try{
            lock.lock();
            while(count >= MAX_MESSAGE) {
                System.out.println("messages full, please wait");
                writeCondition.await();
            }
            messages[writeIndex] = message;
            System.out.println("put message:"+message.toString());
            if(++writeIndex == MAX_MESSAGE)
                writeIndex = 0;
            count++;
            readCondition.signal();
        } finally {
            lock.unlock();
        }
    }

    public void get() throws InterruptedException{
        try{
            lock.lock();
            while(count==0) {
                System.out.println("messages is empty, please wait");
                readCondition.await();
            }

            Object message = messages[readIndex];
            System.out.println("get message:"+message.toString());
            if(++readIndex == MAX_MESSAGE)
                readIndex = 0;
            count--;
            writeCondition.signal();
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        ConditionTest2 conditionTest2 = new ConditionTest2();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for(int i = 0; i < 200; i++)
                        conditionTest2.put(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for(int i = 0; i < 220; i++)
                        conditionTest2.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t2.start();
        t1.start();
    }
}
