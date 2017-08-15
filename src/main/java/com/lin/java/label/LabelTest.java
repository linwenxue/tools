package com.lin.study.java.label;

/**
 * Created by linwenxue on 2015/8/19.
 */
public class LabelTest {

    public static void main(String[] args) {
        PAGE:
        for(int i = 0; i < 10; i++) {
            System.out.println("外层for循环i:"+i);
            for(int j = 0; j < 10; j++) {
                if(j==1) {
                    System.out.println("里层for循环j:"+j);
                    break PAGE;
                }
            }
        }
    }
}
