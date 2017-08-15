package com.lin.study.java.innerclass;

/**
 * Created by linwenxue on 2015/2/1.
 * Desc:
 * 其中我们定义了两个文件，一个文件是一个接口类AInterface，一个是B文件;
 * 在B类中，的 show()方法中我们使用了局部内部类的方式创建了类Man,
 * Man class继承接口并实现方法，随后使用该类。
 */
public class B {
    public void show() {
        class Man implements AInterface {
            @Override
            public void show() {

            }
        }
        Man man = new Man();
        man.show();
    }
}
