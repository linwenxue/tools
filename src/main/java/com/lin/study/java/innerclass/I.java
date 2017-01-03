package com.lin.study.java.innerclass;

/**
 * Created by linwenxue on 2015/2/1.
 * Desc:
 * 初始化
 * A 因为有一个隐藏的引用，所以必须是H 的实例才能进行初始化出A 类；
 * 而B 类则是因为是在H 类中以静态方式存在的类，所以需要 new H.B()；
 * 之所以能直接使用new B()，与该 main 方法在 H 类中有关，因为本来就在 H类中，
 * 所以直接使用 H类的静态属性或者方法可以不加上：“H.”  在前面。
 */
public class I {
    int a = 1;

    public class A {
        public void Show() {
            System.out.print("a:" + a);
        }
    }

    public static class B {
        public void Show(I i) {
            System.out.print("a:" + i.a);
        }
    }

    public static void main(String[] args) {
        I i = new I();
        //A a = new A(); //错误做法
        A a1 = i.new A();
        B b = new B();
        //B b1 = h.new B();//错误做法
        B b3 = new B();
    }
}
