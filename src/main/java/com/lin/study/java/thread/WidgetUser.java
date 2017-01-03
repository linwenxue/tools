package com.lin.study.java.thread;

/**
 * Created by linwenxue on 2015/1/9.
 */
public class WidgetUser extends Thread{
    private WidgetMaker maker;

    public WidgetUser(String name, WidgetMaker maker) {
        super(name);
        this.maker = maker;
    }

    public void run() {
        while (true) {
            Widget w = maker.waitForWidget();
            System.out.println(getName() + " got a widget");
        }
    }

    public static void main(String[] args) {
        WidgetMaker maker = new WidgetMaker();
        maker.start();
        new WidgetUser("Tom", maker).start();
        new WidgetUser("Jack", maker).start();
        new WidgetUser("Marry", maker).start();
    }
}
