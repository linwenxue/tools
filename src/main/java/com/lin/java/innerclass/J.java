package com.lin.java.innerclass;

/**
 * Created by linwenxue on 2015/2/1.
 * Desc:
 * 继承普通内部类
 * 也就是在构造方法中，传递一个 I 的引用进去，并调用 I 实例的 super() 方法，才能进行实例化。
 * 使用的话应该这样：
 */
public class J extends I.A{
    public J(I i){
        i.super();
    }

    public static void main(String[] args) {
        I i = new I();
        J j = new J(i);
    }
}
