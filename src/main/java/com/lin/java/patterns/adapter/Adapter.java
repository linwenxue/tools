package com.lin.java.patterns.adapter;

import com.google.common.base.Strings;

/**
 * Created by linwenxue on 2015/1/21.
 */
public class Adapter extends Adaptee implements Target {
    @Override
    public void getHeadset2() {
        super.getHeadset3();
        //this.getHeadset3();
    }

    public static void main(Strings[] args) {
        new Integer("1");
        Target target = new Adapter();
        target.getHeadset2();
    }
}
