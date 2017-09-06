package com.lin.java.jdktimer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

/**
 * Created by linwenxue on 2015/1/5.
 */
public class JDKTimerTest extends java.util.TimerTask {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new JDKTimerTest(), 0, 3000);
        SimpleDateFormat dateformat1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
        String a1=dateformat1.format(new Date());
        System.out.println("first time:" + a1);
    }

    @Override
    public void run() {
        System.out.println("program start...");
        SimpleDateFormat dateformat1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
        String a1=dateformat1.format(new Date());
        System.out.println("program ending, time:" + a1);
    }
}
