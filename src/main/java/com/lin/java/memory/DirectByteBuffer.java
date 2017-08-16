package com.lin.java.memory;

import sun.nio.ch.DirectBuffer;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/**
 * 分配堆外内存，系统内存及释放内存
 * Created by wenxuelin on 2017/7/13.
 */
public class DirectByteBuffer {
    public static void main(String[] args) throws Throwable{
        //分配512MB直接缓存
        ByteBuffer bb = ByteBuffer.allocateDirect(1024*1024*512);
        TimeUnit.SECONDS.sleep(10);
        //清除直接缓存
        ((DirectBuffer)bb).cleaner().clean();
        TimeUnit.SECONDS.sleep(10);
        System.out.println("ok");
    }
}
