package com.lin.java.exception;

import java.io.IOException;

/**
 * Created by linwenxue on 2015/3/17.
 */
public class ExceptionTest {

    public static void main(String[] args) {
        try {
            throw new IOException("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("异常被捕获后，程序继续往下执行");
    }
}
