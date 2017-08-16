package com.lin.java.innerclass;

/**
 * Created by linwenxue on 2015/2/1.
 * Desc:
 * 从代码中可以看出，内部类可以定义到很多地方，常用的是成员变量中（B），方法中也叫局部内部类（C），作用域中（D）
 */
public class A {
    class B {

    }

    public void pint() {
        class C {
        }
        new C();
    }

    public void pint(boolean b) {
        if (b) {
            class D {
            }
            new D();
        }
    }
}
