package com.lin.java.patterns.templet;

/**
 * Created by linwenxue on 2015/3/12.
 */
public class BaseTemplet implements Templet{

    public void test() {
        System.out.println("test() is processing.....");
    }

    public void before(){
        System.out.println("nothing .....");
    }

    public void doTest() {
        before();
        test();
        after();
    }

    public void after() {
        System.out.println("nothing.....");
    }

    public static void main(String[] args) {
        Templet t = new BaseTemplet();
        t.doTest();
        System.out.println("*********************************************");
        t = new SubTemplet();
        t.doTest();
    }
}
