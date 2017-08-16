package com.lin.java.patterns.decorator;

/**
 * Created by linwenxue on 2015/1/21.
 */
public class Employee implements Person{
    @Override
    public void doCoding() {
        System.out.println("程序员加班写程序啊，写程序，终于写完了。。。");
    }
}
