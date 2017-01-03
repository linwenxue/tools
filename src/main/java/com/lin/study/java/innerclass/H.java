package com.lin.study.java.innerclass;

/**
 * Created by linwenxue on 2015/2/1.
 * Desc:
 * 1、其权限级别是单方向的；静态嵌套类 A 对其包含类 H 完全透明；但 H 并不对 A 透明。
 * 2、这个是为什么呢？为什么就是多一个 static 的修饰就这么完全不同？
 *    很好理解，两个独立的类；本来就无法直接使用，必须有引用才能调用其属性与方法。
 *
 * 3、但是最开始上面的内部类是怎么回事？难道是闹鬼了？上面的内部类没有传递引用的啊；为啥加上一个 static 就不行了？
 *    在没有加static的内部类，在初始化时会把当前主类的引用默认传递给内部类
 */
public class H {
    int a1 = 0;
    private int a2 = 0;
    protected int a3 = 0;
    public int a4 = 0;

    private void show(){
        A a = new A();
        System.out.println("b1:" + a.b1);
        System.out.println("b2:" + a.b2);
        System.out.println("b3:" + a.b3);
        System.out.println("b4:" + a.b4);

    }

    private static class A{
        int b1 = 0;
        private int b2 = 1;
        protected int b3 = 2;
        public int b4 = 3;

        //静态内部类不具有访问主类的成员变量和方法的权限
        private void print(){
            /*System.out.println("a1:"+a1);
            System.out.println("a2:"+a2);
            System.out.println("a3:"+a3);
            System.out.println("a4:"+a4);
            show();*/
        }

        private void print(H h) {
            System.out.println("a1:" + h.a1);
            System.out.println("a2:" + h.a2);
            System.out.println("a3:" + h.a3);
            System.out.println("a4:" + h.a4);

            h.show();
        }
    }

    public static void main(String[] args) {
        new A().print(new H());
    }
}
