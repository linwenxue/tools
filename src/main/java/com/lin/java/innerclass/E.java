package com.lin.study.java.innerclass;

/**
 * Created by linwenxue on 2015/2/1.
 * Desc:
 * 匿名内部类的使用方式，OnClickListener 是一个接口类，接口类是无法直接new 一个实例的；这里也并不是那样，
 * 而是new 了一个其他的类，该类是匿名的，也就是没有名字，只不过该类实现了 OnClickListener接口类中的方法
 */
public class E {
    void initButton() {
        Button b1 = new Button();
        b1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(Button v) {

            }
        });

        Button b2 = new Button();
        b2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(Button v) {

            }
        });
    }
}
