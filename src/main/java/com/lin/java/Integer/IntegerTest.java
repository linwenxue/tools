package com.lin.java.Integer;

/**
 * 自动装箱和拆箱测试类
 * 测试显示自动装箱和拆箱可能有不确定性，
 * 所以实际编程尽量避免使用
 * Created by linwenxue on 2015/3/18.
 */
public class IntegerTest {
    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;

        System.out.println(c == d);//true
        System.out.println(e == f);//false, why?
        System.out.println(e.intValue() == f.intValue());//true
        System.out.println(c == (a + b));//true
        System.out.println(c.equals(a + b));//true
        System.out.println(g == (a + b));//true
        System.out.println(g.equals(a + b));//false
    }
}
