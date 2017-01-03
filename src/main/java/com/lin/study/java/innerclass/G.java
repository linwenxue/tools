package com.lin.study.java.innerclass;

/**
 * Created by linwenxue on 2015/2/1.
 * Desc:
 * 静态内部类
 * 其中类 A 使用了 static ，所以是静态嵌套类，
 * 在这里使用private 修饰；那么该类只能在 E 类中进行实例化；无法在 其他文件中实例化。
 */
public class G {
    private void show(){
        new A();
    }

    private static class A{

    }
}
