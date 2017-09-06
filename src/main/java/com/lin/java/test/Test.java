package com.lin.java.test;

/**
* @Description: 测试类
* @author linwenxue
* @date 2015/12/30
*/
public class Test {

    public static void main(String[] args) throws Exception {
        //System.out.println(myPow(-2.00000,2));
        //System.out.println(Double.valueOf(1e-15));
    }

    public static double myPow(double x, int n) {
        if(x == 1 || n == 0) return 1;

        if(x == -1) {
            return n%2 == 0 ? 1 : -1;
        }

        if(n < 0) {
            if(-n == Integer.MIN_VALUE) {
                n = Integer.MAX_VALUE;
            } else {
                n = -n;
            }
            return 1/myPow(x, n);
        }

        double tmp = myPow(x, n/2);
        return n%2==0 ? tmp*tmp : tmp*tmp*x;
    }

    public static double test(double x, int n) {
        if(x == 1 || n == 0) return 1;
        double r;
        double tmp;
        if(n < 0) {
            r = myPow(1/x, -n);
        } else if(n == 1) {
            r = x;
        } else if(n>1 && n%2==0) {
            tmp = myPow(x, n/2);
            r = tmp*tmp;
        } else {
            tmp = myPow(x, n/2);
            r = tmp*tmp*x;
        }
        return r;
    }

    public static double sqrt(double c) {
        if (c < 0) return Double.NaN;
        double err = 1e-15;
        double t = c;
        while(Math.abs(t - c/t) > err * t) {
            t = (c / t + t) / 2.0;
            System.out.println(t);
        }
        return t;
    }
}
