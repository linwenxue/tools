package com.lin.study.java.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wenxuelin on 2017/5/23.
 */
public class BlockingQueueTest {

    public static void main(String args[]) throws  Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        ArrayBlockingQueue queue = new ArrayBlockingQueue(10);
        executorService.submit(new BlockingQueuePut(queue));
        //Thread.sleep(2000);
        executorService.submit(new BlockingQueueTake(queue));
    }

}


class BlockingQueuePut implements Runnable{
    private BlockingQueue queue;

    public BlockingQueuePut(){}

    public BlockingQueuePut(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            int i=0;
            while (true) {
                System.out.println("put element a....");
                queue.put("a");
                i++;
                if(i==10000) break;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class BlockingQueueTake implements Runnable {
    private BlockingQueue queue;

    public BlockingQueueTake(){}

    public BlockingQueueTake(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            int i=0;
            while(true) {
                System.out.println("获取元素："+queue.take());
                i++;
                System.out.println("获取元素个数："+i);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
