package com.lin.java.patterns.templet;

/**
 * Created by linwenxue on 2015/3/12.
 */
public class SubTemplet extends BaseTemplet{

    public void before(){
        System.out.println("chirdren class doBefore() is processing.....");
    }

    public void after() {
        System.out.println("chirdren class doAfter() is processing.....");
    }
}
