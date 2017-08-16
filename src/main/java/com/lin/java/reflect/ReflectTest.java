package com.lin.java.reflect;

import java.lang.reflect.Field;

/**
 * Created by wenxuelin on 2017/6/2.
 */
public class ReflectTest {

    public static void main(String[] args) throws Exception {
        getDeclaredField();
    }


    private static void getDeclaredField() {
        //获取Foo类的声明的属性，declared不包括继承过来的
        Field[] fields = Foo.class.getDeclaredFields();
        for(Field f : fields) {
            System.out.println(f.getName());
        }
    }
}
