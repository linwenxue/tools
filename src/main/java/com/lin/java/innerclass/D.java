package com.lin.java.innerclass;

/**
 * Created by linwenxue on 2015/2/1.
 * Desc:
 * D类对内部类C具有完整的访问权限，也能直接访问私有属性 私有方法，在这里似乎私有的限制已经失效了一般，
 * 这个让我想起了以前看见过一个面试：在 Java 中 private 修饰何时会失效。
 */
public class D {
    class C {
        private int a = 20;
        private void a(){
            System.out.println("D.A:" + a);
        }
    }

    void show(){
        C c = new C();
        c.a();
        System.out.println("C.A:" + c.a);
    }

    public static void main(String[] args) {
        new D().show();
    }
}
