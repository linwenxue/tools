package com.lin.hadoop.learn;

/**
 * Created by linwenxue on 2014/11/8.
 */
class test implements Cloneable{
    public static int k = 0;
    public static test t1 = new test("t1");
    public static test t2 = new test("t2");
    public static int i = print("i");
    public static int n = 99;

    public int j = print("j");

    {
        print("构造快");
    }

    static {
        print("构造快");
    }

    public test(String str) {
        System.out.println((++k) + ":" + str + "    i=" + i + "  n=" + n);
        ++n; ++ i;
    }

    public static int print(String str){
        System.out.println((++k) +":" + str + "   i=" + i + "   n=" + n);
        ++n;
        return ++ i;
    }

    public static void main(String[] args){
        test t = new test("init");
    }
}