package com.lin.java.io;


import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by linwenxue on 2015/1/20.
 */
public class NioTest {
    private static final int BSIZE = 1024;

    public static void main(String[] args) {
        FileChannel fc;
        try {
            //FileOutputStream写入文件
            fc = new FileOutputStream("data.txt").getChannel();
            fc.write(ByteBuffer.wrap("some text ".getBytes()));
            fc.close();
            //RandomAccessFile写入文件，可以操作文件位置
            fc = new RandomAccessFile("data.txt","rw").getChannel();
            fc.position(fc.size());
            fc.write(ByteBuffer.wrap("some more".getBytes()));
            fc.close();
            //FileInputStream读取文件
            fc = new FileInputStream("data.txt").getChannel();
            ByteBuffer buff = ByteBuffer.allocate(BSIZE);
            fc.read(buff);
            /**
             * buff.flip()
             * API上面翻译的是："反转此缓冲区。首先对当前位置设置限制，然后将该位置设置为零。如果已定义了标记，则丢弃该标记。"
             * 说的意思就是：将缓存字节数组的指针设置为数组的开始序列即数组下标0
             */
            buff.flip();
            while(buff.hasRemaining()) {
                System.out.print((char) buff.get());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
