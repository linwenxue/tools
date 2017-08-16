package com.lin.java.innerclass;

/**
 * Created by linwenxue on 2015/2/1.
 * Desc:
 * 可以看出，内部类 D 对类 C 具有完整的访问权限
 */
public class C {
    int a = 1;
    private int b = 2;
    protected int c = 3;
    public int d = 4;

    void a() {
        System.out.println("A:" + a);
    }

    private void b() {
        System.out.println("B:" + b);
    }

    protected void c() {
        System.out.println("C:" + c);
    }

    public void d() {
        System.out.println("D:" + d);
    }

    class D {
        void show() {
            int max = a + b + c + d;
            a();
            b();
            c();
            d();
            System.out.println("Max:" + max);
        }
    }

    public static void main(String[] args) {
        D d = new C().new D();
        d.show();
    }
}
