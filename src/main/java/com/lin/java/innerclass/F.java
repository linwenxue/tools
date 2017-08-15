package com.lin.study.java.innerclass;

/**
 * Created by linwenxue on 2015/2/1.
 * Desc:
 * 等同于E中的实现方式
 * 这里就是先建立类，继承自接口；而后赋值到 Button 中。
 * 要说两者的区别与好处，这个其实看具体的使用情况吧；如果你的按钮很多，但是为了避免建立太多类；
 * 那么可以建立一个回调类，然后都赋值给所有的按钮，不过最后就是需要在 onClick方法中进行判断是那个按钮进行的点击。
 */
public class F {
    void initButton1() {
        Button b1 = new Button();
        b1.setOnClickListener(new Listener1());

        Button b2 = new Button();
        b2.setOnClickListener(new Listener2());
    }

    class Listener1 implements OnClickListener {

        @Override
        public void onClick(Button v) {

        }
    }

    class Listener2 implements OnClickListener {

        @Override
        public void onClick(Button v) {

        }
    }
}
